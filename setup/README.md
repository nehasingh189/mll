# Installing the Northeastern Media Licensing Lab server

### Setting up the environment
Setting up the environment only needs to be done once. When setting up MLL
on a new server, run `sudo ./setup_environment.sh` to set up the server.
This will install and configure Java, MySQL, Tomcat, Razuna, and everything 
else needed by the application. 

This is an interactive application. Look at the MLL confluence page 
for information about usernames and passwords that need to be typed in. They
do not exists in the codebase for security reasons.

Running this script creates a `razuna_ids` file. This is a very important file that
must not be deleted. It contains all the API keys used for Razuna by MLL. Its needed
to release the MLL server. Don't upload it to version control though. That compromises 
security of the application.

Don't run this script twice on the same server. Its not necessary and hasn't been
tested.

### Releasing MLL
Once the environment has been set up, the MLL server can be released. 
Simply run `./release.sh [YOUR_RAZUNA_IDS_FILE] [OPTIONAL_GIT_HASH]` to
release MLL. By default, it will release whatever is in the `master` branch,
but you can give git hash as a second argument to release a specific version
for testing purposes. The second argument can also be a tag or branch name,
so if you want to keep a special "release" branch, thats cool too.

