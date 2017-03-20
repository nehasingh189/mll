#!/usr/bin/env bash
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
  echo "Leaving $WORK_DIR for debugging purposes"
}

# register the cleanup function to be called on the EXIT signal
trap cleanup EXIT

# implementation of script starts here
if [ "$EUID" == 0 ]
  then echo "Please run as non-root (without sudo)"
  exit
fi

if [ -z "$1" ]; then
    echo "You must supply a razuna_ids file to release"
    exit
fi

source /etc/environment
source $1

echo "Installing MLL"
cd ${WORK_DIR}
git clone https://github.ccs.neu.edu/CS4500Sp17/Music-Licensing-Laboratory.git mll
cd mll


if [ -n "$2" ]; then
    echo "Checking out version of MLL with hash: $2"
    git checkout $2
fi

echo "Inserting Razuna API key and IDs"

sed -i "s/RAZUNA_TITLE=\".*\"/RAZUNA_TITLE=\"$TITLE_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_BITS_PER_RATE=\".*\"/RAZUNA_BITS_PER_RATE=\"$BITS_PER_RATE_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_COPY_RIGHT_NUMBER=\".*\"/RAZUNA_COPY_RIGHT_NUMBER=\"$COPYRIGHT_NUMBER_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_COPY_RIGHT_DATE=\".*\"/RAZUNA_COPY_RIGHT_DATE=\"$COPYRIGHT_DATE_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_PUBLISHING_COMPANY=\".*\"/RAZUNA_PUBLISHING_COMPANY=\"$PUBLISHING_COMPANY_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_PRO=\".*\"/RAZUNA_PRO=\"$PRO_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_LYRICS=\".*\"/RAZUNA_LYRICS=\"$LYRICS_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_PRIMARY_GENRE=\".*\"/RAZUNA_PRIMARY_GENRE=\"$PRIMARY_GENRE_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_SEC_GENRE=\".*\"/RAZUNA_SEC_GENRE=\"$SECONDARY_GENRE_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "s/RAZUNA_ARTISTS=\".*\"/RAZUNA_ARTISTS=\"$ARTISTS_ID\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java

sed -i "RAZUNA_URL=\".*\"/RAZUNA_URL=\"http://localhost:8081/razuna/raz1/dam/index.cfm\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "RAZUNA_API_URL=\".*\"/RAZUNA_API_URL=\"http://localhost:8081/razuna/global/api2/\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java
sed -i "RAZUNA_KEY=\".*\"/RAZUNA_KEY=\"$RAZUNA_KEY\"/g" ${WORK_DIR}/mll/src/main/java/mll/utility/Configuration.java


mvn -Dmaven.test.skip=true clean package

sudo service tomcat8 stop

if [ -a /var/lib/tomcat8/webapps/MLL.war ]; then
    sudo rm /var/lib/tomcat8/webapps/MLL.war
fi

if [ -d /var/lib/tomcat8/webapps/MLL ]; then
    sudo rm -rf /var/lib/tomcat8/webapps/MLL
fi

sudo cp target/MLL-*.war /var/lib/tomcat8/webapps/MLL.war

sudo service tomcat8 start
echo "Release completed"