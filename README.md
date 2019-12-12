# Parkings

## Main feature
REST server 

Display parkings information:
- name
- free spaces
- geolocalization

## Detail
The server exposes one REST service:
- parkings?city=<given_city>

This service currently support city Rennes.

The server has two modes:
- One "straightforward mode": each REST call sends a request to Rennes API
- One "cache mode": Rennes API response is cached when server starts and this cache is refreshed every seconds

## Usage
Cache deactivated:
- java -jar rennes-0.0.1-SNAPSHOT.jar

Cache activated:
- java -jar rennes-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=cache-enable

## Tech used
- Maven
- Java 11
- Spring boot 2.2.2

## Project structure
- Controller that exposes REST and calls DAO
- DAO that centralized parking fetching from different sources
- Rennes REST API is the only managed datasource for the moment

## TODO
- Blueprint of architecture
- Javadoc
- Error handling
- City management
- UT/IT
- Performance tests
- Cache refresh rate and page size in properties
