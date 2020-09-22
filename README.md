# Tradeshift Tree Challenge

Created an application which runs an embedded Tomcat server already configured by the Spring framework starting on the port 8080. To run the application, I create a Dockerfile which contain all the steps to create an image of the application.
Before creating the image of the docker run ```mvn clean package``` to compile your code and package it into a jar, which it will be used inside the docker image.
For creating the image of the application use command inside the directory where you have cloned the repository: ```docker build -t tree-challenge```, then to run it use ```docker run -p 3333:8080 tree-challenge```.

After all is up and running you need access the the REST API via HTTP client, for this purpose I am using Postman and I let you a collection of the Tree API.
