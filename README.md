Build the service:
mvn clean install

Staring the Tomcat Server:
Deploy your built jar: "java -jar <Path Of the Jar>"

Endpoints:
Search by keyword:
http://localhost:8080/searchProducts/{search_by_keyword}
Eg: http://localhost:8080/searchProducts/drone

Health:
http://localhost:8080/actuator/health

Metrics:
http://localhost:8080/actuator/metrics