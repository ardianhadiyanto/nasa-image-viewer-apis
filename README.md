# NASA-Image-Viewer-APIs

## About

This API exposes endpoints that allow user to view images that were taken by NASA's rovers' cameras. 

When the API 
receives a request for an image, it will first look in a local storage directory to see if the requested image 
exists. If not, the API will then download the image from the specified source and store it in the local storage 
directory for 
further use. To ensure the uniqueness of the stored image in local storage directory, a SHA-256 hash is generated 
from the image URL, which will then be used as the filename of the stored image.

There are 4 NASA rovers:
1. Curiosity
2. Spirit
3. Opportunity
4. Perseverance

The images supported will be from the below dates:
1. February 2, 2017
2. June 2, 2018
3. July 13, 2016
4. April 31, 2018

## Exposed Endpoints

- GET list of images: `/api/v1/images`
  http://localhost:8080/api/v1/images
  returns:
  ```json
  [
    {
        "rover": "Curiosity",
        "camera": "FHAZ",
        "dateTaken": "2017-02-27",
        "imageUrl": "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01622/opgs/edr/fcam/FLB_541484941EDR_F0611140FHAZ00341M_.JPG"
    },
    ...
  ]
  ```
  
- POST image: `/api/v1/images`
  http://localhost:8080/api/v1/images with JSON body:
  ```json
  {
    "url": "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01622/opgs/edr/fcam/FLB_541484941EDR_F0611140FHAZ00341M_.JPG"
  }
  ```
  returns the requested image at the specified URL.

## Getting Started

### Prerequisites

To work with the source code, please ensure that you are utilizing an IDE that has a nice support for Java tooling.

Since this project will send requests to NASA APIs, it will need a valid token, which you can get from https://api.
nasa.gov/. By default, this project will use the special DEMO_KEY token. If you have your own token, you can set
the `nasa.api.key` field in `application.properties` file to use your token.

### From a jar
1. Build the jar
  ```
  mvn package
  ```
2. Run the jar
  ```
  java -jar nasa-image-viewer-server-0.0.1-SNAPSHOT.jar
  ```

### From Docker Image
1. Build the image
  ```
  docker build -t nasa-image-viewer-apis .
  ```
2. Run the image
  ```
  docker run -p 8080:8080 --name nasa-image-viewer-apis nasa-image-viewer-apis
  ```

## Built With
1. Java 8
2. Maven
3. Spring Boot
4. JUnit 5
5. Docker
6. PMD
