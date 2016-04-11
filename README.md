# Festivity Sample Project

# Prerequisites
- mongodb installed and running
- java 8 SDK installed
- gradle version 2 or greater

# Running the application
1. Clone the repository
2. Access the project folder *e.g. cd restsampleproject*
3. Execute the startup command *gradle bootRun*

# Available Sample URLs
- GET http://localhost:8080/festivity/findAll
- http://localhost:8080/swagger/index.html

# XML files location
src/main/resources/xml/festivities.xml

# Sample JSON bodies
POST, Create:
{"name": "festivityName_NEW","startDate": "2016-01-01","endDate": "2016-01-01","locationName": "Bogota"}
