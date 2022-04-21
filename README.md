# retailer-reward

# How to run project

* To run the project you must first build the jar file.
* To build the jar file you can utilize the gradle wrapper
  * `./gradlew clean build`
* You can find the jar file in the `build/libs` directory of the project.
* To run the jar file execute the command `java -jar retailer-reward-0.0.1-SNAPSHOT.jar`

## API

* This project is built using gradle.
* It has 3 end points
  * Two of them are Get endpoint
    * `localhost:8080/getUniqueCustomers` can be used to find id's of unique customers
    * `localhost:8080/getRewardsByCustomerId/{id}` will get you reward for a particular customer
  * Post endpoint 
    * Post endpoint can be used to create new transaction.
    * `localhost:8080/createTransaction`
      * Keep in mind that the amount is accepted in the form of cents
      * For Example:
        * $1 = 100


## Swagger implementation

* I have implemented swagger to enable some easy API testing.
* You can access swagger at `localhost:8080/swagger-ui/index.html`


## H2 database

* For quick prototyping purposes this application has been built using H2 database.
* You can access the H2 database from `localhost:8080/h2-console` url.

## Test

* For testing and demonstration purposes there are couple of customers have been loaded with 3 transactions each. By using spring commandline runner.

