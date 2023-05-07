FROM maven:3.9.1-jdk-17

WORKDIR /BankServerApplication
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run