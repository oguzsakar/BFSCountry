# Country Path Finder


## Description

* Spring Boot, Maven
* Data link: https://raw.githubusercontent.com/mledoze/countries/master/countries.json
* The application exposes REST endpoint /routing/{origin}/{destination} that
returns a list of border crossings to get from origin to destination
* Single route is returned if the journey is possible
* Algorithm needs to be efficient
* If there is no land crossing, the endpoint returns HTTP 400
* HTTP request sample (land route from Czech Republic to Italy):
○ GET /routing/CZE/ITA HTTP/1.0 : {
"route": ["CZE", "AUT", "ITA"] }
## Getting Started

### Executing program

Make sure you have jdk 17 installed on your system and The JAVA_HOME environment variable is  defined correctly,

```
git clone

mvn spring-boot:run

curl 'http://localhost:8080/findPath/CZE/AUT'

```