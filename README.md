# Team 3 Practice Sprint

## Dropwizard API

### Environment Variables Required
1. DB_NAME
2. DB_HOST
3. DB_USERNAME
4. DB_PASSWORD
5. SECRET

### To build the application

```
mvn clean install -DskipTests=true
mvn -B package --file pom.xml
```

### To Run Tests
`mvn clean integration-test`

### To Run The Application
`java -jar /code/target/JavaWebService-1.0-SNAPSHOT.jar server /code/config.yml`

### To Build Docker Image

Make sure the docker engine is running in the background

`docker build --build-arg DB_HOST=${DB_HOST} --build-arg DB_NAME=${DB_NAME} --build-arg DB_USERNAME=${DB_USERNAME} --build-arg DB_PASSWORD=${DB_PASSWORD} -t Team-3-API:0.1 .`

### To Run The Docker Image
`docker run Team-3-API:0.1`
