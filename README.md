# Semester5---Project-Enterprise-Computing
Project for Enterprise Computing course.

## Starting the project:

1. Run Maven Lifecycle package with: `mvn package`
2. (If run locally) Start Core:
   1. Change Dir to advance-ticket-sale/core/target
   2. Run with `java -jar core-1.0-jar-with-dependencies.jar`
3. Start a client:
   1. Change Dir to advance-ticket-sale/client.javafx/target
   2. Run with `java -jar client.javafx-1.0-jar-with-dependencies.jar`
   
You might need to add the following to your runtime policies:<br/>
The policy file can be found inside your JRE:<br/>
Example Path:<br/> `C:\Program Files\Java\jdk1.8.0_191\jre\lib\security\java.policy`<br/>
Add the following to the file at the specified place. 
```java
grant {
  /* OHTER Permissions */
  permission javax.management.MBeanTrustPermission "register";
};
```
