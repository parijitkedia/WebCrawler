# WebCrawler
 
This is a web crawler example built using gradle.

# Installation Instructions

To install gradle please follow the necessary instructions based on the OS - `https://gradle.org/install/`. 

The following instructions are to be run on MacOS - 
1. Use brew and install via the command - `brew install gradle`, if not already installed
2. Import the project in your preferred IDE
3. In the file `ConfigProperties.java` located in /app/src/main/java/Configuration, please change the following properties 
   a. String value called DATABASE_PATH. This should contain the absolute path of your sqlite database
   b. String value called TABLE_NAME. This is user preference and can be left as it is.
5. Run `./gradlew clean build` in the root directory
6. Run `./gradlew run` to run the program
