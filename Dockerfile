FROM maven:latest

WORKDIR /code
COPY . /code

ARG DB_HOST
ENV DB_HOST ${DB_HOST}

ARG DB_NAME
ENV DB_NAME ${DB_NAME}

ARG DB_USERNAME
ENV DB_USERNAME ${DB_USERNAME}

ARG DB_PASSWORD
ENV DB_PASSWORD ${DB_PASSWORD}

ARG SECRET
ENV SECRET ${SECRET}

RUN mvn clean install -DskipTests=true
RUN mvn -B package --file pom.xml
EXPOSE 8080


CMD ["java","-jar", "/code/target/JavaWebService-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]