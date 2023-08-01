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