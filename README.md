# MLL
Media Licensing Lab

Student run Media Licensing Project


### Local Development

#### Set up Razuna and Database Configurations
You will need to manually configure Razuna and Database to point either locally or at the aws server.  
Razuna configurations are at 'src/main.java/mll/utility/Configuration.java'  
Database configurations are at 'src/main/resources/hibernate.cfg.xml'  

#### Set up Gulpfile 
Inside of gulpfile.js in the src/main/webapp directory, uncomment the correct line in the gulp 'templates' function that
corresponds to your OS. This is so when Gulp builds the project files, it will use either '/' or '\' in the pathnames
of the files, which is different for Window vs Mac/Linux.

#### Useful Gulp Commands
This project uses gulp as the javascript build utility and task runner.
Run these commands from the src/main/webapp directory.

**gulp build** - builds the code un-minified in the dist directory  
**gulp build --prd** - builds the code minified for deploying to production  
**gulp develop** - automatically watches for file changes and rebuilds each time. Useful for making small UI tweaks    
**gulp test** - runs the karma tests. Test configurations (browser, etc.) are specified in karma.conf.js  

#### Test Account Information
Here are some existing credentials that can be used for testing and development:

**Admin**  
email: testAdmin@gmail.com   
password: 12345678  

**Musician**  
email: testMusician@gmail.com  
password: 12345678  

