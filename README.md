# country-info-service


## Requirements:

~ Java 17 or higher
~ Maven 3.9.4

## Project setup and dependency:

1. Clone this repository to your local machine:
   https://github.com/vasanthekumar/country-info-service.git

   All microservice applications relies on:
    1) Config Server – config-server: for centralized configurations    
       a) Centralized configuration properties:  https://github.com/vasanthekumar/cloud-country-service-config-store.git

    2) Eureka server – discovery-service: Automated service registry and dynamic service discovery.

Configuration verification before stating the MS application:

1. Properties in cloud-country-service-config-store application configuration
   Path: cloud-country-service-config-store/country-info-service/country-info.properties
     https://github.com/vasanthekumar/cloud-country-service-config-store/blob/main/country-info-service/country-info.properties
    ~country.info.url= <https://restcountries.com/v3.1/>
2. bootstrap properties of the application country-application.
   Path: src/main/resources/bootstrap.properties
   Note: If you are not running the config server on port 8191 please configure below property in bootstrap properties file accordingly.
    ~spring.cloud.config.uri= http://localhost:<port> eg: http://localhost:8191

Make Sure before starting the microservice application (here country-service) Config server and Eureka server are up and running.

### Build and run.
1. Navigate to the project directory:
   cd country-info-service
2. Build the application using Maven
   mvn clean install
3. Run the application:
   java -jar country-info-0.0.1-SNAPSHOT.jar