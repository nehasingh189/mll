#!/bin/bash
set -e
# the directory of the script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# the temp directory used, within $DIR
WORK_DIR=`mktemp -d -p "$DIR"`

# check if tmp dir was created
if [[ ! "$WORK_DIR" || ! -d "$WORK_DIR" ]]; then
  echo "Could not create temp dir"
  exit 1
fi

# deletes the temp directory
function cleanup {
  rm -rf "$WORK_DIR"
  echo "Deleted temp working directory $WORK_DIR"
}

# register the cleanup function to be called on the EXIT signal
trap cleanup EXIT

# implementation of script starts here
if [ "$EUID" -ne 0 ]
  then echo "Please run as root (with sudo)"
  exit
fi

echo ""
echo "Setting up Media Licensing Lab environment"
echo "Do not run this script twice on the same machine"
echo "This script will install an operational environment for the Media Licensing Lab application."
echo "This script is meant for Ubuntu. It will not work on Windows or Mac."
echo "If you have already run this script, please exit now (Ctrl-c)"
sleep 5

cd "$WORK_DIR"
apt-get update && apt-get -y install default-jdk maven tomcat8 build-essential git
service tomcat8 stop
export JAVA_HOME=/usr/lib/jvm/default-java
echo "export JAVA_HOME=$JAVA_HOME" >> /etc/environment

echo "Please enter the Jasypt password. This can be found on Confluence."
read -p "Jasypt Password: " JASYPT_PASS
touch /jasypt_pass
echo ${JASYPT_PASS} > /jasypt_pass
echo "export JASYPT_LOCATION=/jasypt_pass" >> /etc/environment

### MySQL Setup ###
echo "Setting up MySQL"
echo "Look up the root MySQL password on confluence. Please enter that here, then press [Enter]:"
read -p "MySQL root password: " MYSQL_ROOT_PASS

debconf-set-selections <<< "mysql-server mysql-server/root_password password $MYSQL_ROOT_PASS"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $MYSQL_ROOT_PASS"
apt-get -y install mysql-client mysql-server expect

SECURE_MYSQL=$(expect -c "
set timeout 3
spawn mysql_secure_installation
expect \"Enter current password for root (enter for none):\"
send \"$MYSQL_ROOT_PASS\r\"
expect \"any other key for No:\"
send \"n\r\"
expect \"root password?\"
send \"n\r\"
expect \"Remove anonymous users?\"
send \"y\r\"
expect \"Disallow root login remotely?\"
send \"y\r\"
expect \"Remove test database and access to it?\"
send \"y\r\"
expect \"Reload privilege tables now?\"
send \"y\r\"
expect eof
")

echo "${SECURE_MYSQL}"

sed -i "s/bind-address/#bind-address/g" /etc/mysql/mysql.conf.d/mysqld.cnf
service mysql restart

echo "Installation of MySQL complete. Starting setup of db schema"

echo "Look up the mll_user database password on confluence. Enter it here and press [Enter]:"
read -p "MySQL user password: " MYSQL_USER_PASS

mysql --user=root --password=${MYSQL_ROOT_PASS} < ${DIR}/mll_schema.sql
#mysql --user=root --password=${MYSQL_ROOT_PASS} < ${DIR}/mll_users_table.sql
mysql --user=root --password=${MYSQL_ROOT_PASS} -e "CREATE USER 'mll_user'@'%%' identified by '$MYSQL_USER_PASS';"
mysql --user=root --password=${MYSQL_ROOT_PASS} -e "GRANT ALL on medialicensinglab.* to 'mll_user'@'%%'; FLUSH PRIVILEGES;"

echo "MySQL setup complete"

### Razuna Setup ###
echo "Setting up Razuna"

apt-get -y install imagemagick ffmpeg dcraw ufraw gpac jq ghostscript build-essential unzip

# ExifTool
cd /opt
wget http://owl.phy.queensu.ca/%7Ephil/exiftool/Image-ExifTool-10.46.tar.gz
gzip -dc Image-ExifTool-10.46.tar.gz | tar -xf -
cd Image-ExifTool-10.46
perl Makefile.PL
make test
sudo make install
cd ${WORK_DIR}

wget http://razuna.org/razuna-tomcat.cfm?v=1.9.1 -O razuna_tomcat.zip
unzip razuna_tomcat.zip -d /opt && sed -i "s/8080/8081/g" /opt/razuna_tomcat_1_9_1/tomcat/conf/server.xml
sed -i "s/8005/8006/g" /opt/razuna_tomcat_1_9_1/tomcat/conf/server.xml
sed -i "s/8443/8444/g" /opt/razuna_tomcat_1_9_1/tomcat/conf/server.xml
echo "Starting up razuna"
/opt/razuna_tomcat_1_9_1/tomcat/bin/startup.sh

echo "You must now setup Razuna manually from the web interface. Go to \$YOUR.IP:8081/razuna and do this."
echo "Create an Administrator account. Please see Confluence for what the username and password should be."
echo "When you have created an Administrator user and have an API key, please enter the key here."
echo "To find the API key, click on \"Users\" on the left side of the landing page. Click the Administrator username, then click \"API Key\""
read -p "Razuna API Key (Make sure this is correct): " RAZUNA_API_KEY

echo "Now setting up Razuna fields necessary for MLL"

RAZUNA_HOST="localhost:8081/razuna"
RAZUNA_ADD_FIELD_STUB="/global/api2/customfield.cfc?method=setfield&api_key=$RAZUNA_API_KEY&field_type=text&field_text="

TITLE_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Title" | jq '.field_id'`
BITS_PER_RATE_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Bits%20Per%20Rate" | jq '.field_id'`
COPYRIGHT_NUMBER_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Copyright%20Number" | jq '.field_id'`
COPYRIGHT_DATE_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Copyright%20Date" | jq '.field_id'`
PUBLISHING_COMPANY_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Publishing%20Company" | jq '.field_id'`
PRO_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Pro" | jq '.field_id'`
LYRICS_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Lyrics" | jq '.field_id'`
PRIMARY_GENRE_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Primary%20Genre" | jq '.field_id'`
SECONDARY_GENRE_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Secondary%20Genre" | jq '.field_id'`
ARTISTS_ID=`curl "$RAZUNA_HOST/${RAZUNA_ADD_FIELD_STUB}Artists" | jq '.field_id'`

if [ -a ${DIR}/razuna_ids ]; then
    rm ${DIR}/razuna_ids
fi

touch ${DIR}/razuna_ids
echo "RAZUNA_KEY=$RAZUNA_API_KEY" >> ${DIR}/razuna_ids
echo "TITLE_ID=$TITLE_ID" >> ${DIR}/razuna_ids
echo "BITS_PER_RATE_ID=$BITS_PER_RATE_ID" >> ${DIR}/razuna_ids
echo "COPYRIGHT_NUMBER_ID=$COPYRIGHT_NUMBER_ID" >> ${DIR}/razuna_ids
echo "COPYRIGHT_DATE_ID=$COPYRIGHT_DATE_ID" >> ${DIR}/razuna_ids
echo "PUBLISHING_COMPANY_ID=$PUBLISHING_COMPANY_ID" >> ${DIR}/razuna_ids
echo "PRO_ID=$PRO_ID" >> ${DIR}/razuna_ids
echo "LYRICS_ID=$LYRICS_ID" >> ${DIR}/razuna_ids
echo "PRIMARY_GENRE_ID=$PRIMARY_GENRE_ID" >> ${DIR}/razuna_ids
echo "SECONDARY_GENRE_ID=$SECONDARY_GENRE_ID" >> ${DIR}/razuna_ids
echo "ARTISTS_ID=$ARTISTS_ID" >> ${DIR}/razuna_ids

echo ""
echo ""
echo "### FINISHED ###"
echo "Done setting up environment! Razuna custom field IDs are in $DIR/razuna_ids. DO NOT DELETE OR LOSE THIS FILE!"
echo "Move is to somewhere safe. It is used when releasing the MLL application."