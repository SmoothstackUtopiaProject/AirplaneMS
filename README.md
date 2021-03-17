# AirplaneMS
Airplane Microservice for Utopia Airlines
## Requirements & Quick Start
##### -Maven
##### -MySQL
`$ mvn spring-boot:run` - run AirplaneMS as a spring boot application. The application will run by default on port `8081`.

Configure the port by changing the `server.port` value in the `application.properties` file which is located in the resources folder.

The application can be run with a local MySQL database. Configure the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in the `application.properties` file according to your needs.
## API
`/airplanes` - GET : Get a list of all the airplanes from the DB.

`/airplanes/{id}` - GET : Get an airplane by id.

`/airplanes` - POST : Create an airplane by providing a correct request body

`/airplanes` - PUT : Update an airplane by providing a correct request body including the id

`/airplanes/{id}` - DELETE : Delete an airplane by id.
