FROM openjdk:11
EXPOSE 8080
ADD target/employee-management-system.jar employee-management-system.jar
ENTRYPOINT ["java","-jar","/employee-management-system.jar"]
