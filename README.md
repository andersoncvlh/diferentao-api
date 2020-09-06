## Base64 String Comparator Web Service
### Spring boot web and Maven

This web service provides 2 HTTP endpoints that accepts JSON base64 encoded binary data on both, as it follows:

    <host>/v1/diff/left
    <host>/v1/diff/right

• The provided data is diff-ed and the results are available on a third end point

    <host>/v1/diff/<ID>

• The results are provided in JSON format as following:

    If equal return that
    If not of equal size just return that
    If of same size provide insight in where the diffs are, actual diffs are not needed.
        § So mainly offsets + length in the data

### Technologies

This project was developed with:
    Spring boot web 2.3.3.RELEASE
    Java 11
    JUnit 4
    JUnit Jupiter 5 
    Apache Maven 3.3.9
    Lombok

### Compile and Package
In order to generate this spring boot app, you should run:
    mvn package

It will clean, compile and generate a uber jar at target dir, e.g. diferentao-api.jar

### Test

It provides some methods to test:
• Integration Tests
    mvn -Dtest=DiffControllerTest test
• Unit Tests
mvn -Dtest=DiffBusinessTest test

Sending data to LEFT endpoint

    curl -i -H "Content-Type: application/json" -X POST -d '{"id": 1, "value":"YWMvZGM="}' http://localhost:9180/diferentao-api/v1/diff/left

Sending data to RIGHT endpoint

    curl -i -H "Content-Type: application/json" -X POST -d '{"id": 1, "value":"YWMvZGM="}' http://localhost:9180/diferentao-api/v1/diff/right

Getting the diff result

    curl -X GET -i http://localhost:9180/diferentao-api/v1/diff/1

### Run
In order to run the web service, run the uber jar simply as following:
    java -jar diferentao-api.jar

By default, the service will be available at 
    http://localhost:9180/diferentao-api
