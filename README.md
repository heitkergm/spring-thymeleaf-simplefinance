# spring-thymeleaf-simplefinance
This is a sample webapp using spring-boot, spring-security, spring-session,
hibernate, JPA, postgres, embedded tomcat, redis, and thymeleaf.

It requires Java 8u121.  OpenJDK is fine, and recommended.  If not, use Oracle
Java and remove the mission control app.

This is a work in progress.  So it is not quite done.  The goal is for it to
show how an app could force https connections, use a database, provide self-registration.

I've implemented a lot of features in the pom.xml:

* Code coverage
* PMD
* FindBugs
* Checkstyle
* jacoco code coverage
* errorprone compiler enhancement
* lombok
* maven wrapper

The generated javadoc properly uses the delomboked source, and provides the
appropriate javadoc directives.

This project also includes the NetBeans project files.  NetBeans has the best
maven integration, IMHO.

You **must** have a recent postgres running on localhost using the default port.
The following postgres configuration is required (easiest using pgadmin):

* add a login role **simplefinance** with password **simplefinance**
* create a database named **simplefinance** owned by the role **simplefinance**
* create a schema named **simplefinance** owned by the role **simplefinancne**
in the **simplefinance** database.

The program will automagically create all database objects in that schema.

You **must** also have redis configured, accepting anonymous connections on
localhost using its default port.

It would be nice to have a mail agent listening on port 25, which would accept
mail sent to "matt@localhost".

To run, make sure nothing is using ports 8080 and 8443, and on the command line,

**./mvnw spring-boot:run**

Point your browser to [http://localhost:8080/simplefinance](http://localhost:8080/simplefinance)