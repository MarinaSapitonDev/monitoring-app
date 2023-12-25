to run this application 
1) open command promt and go to the monitoring-app directory and run "mvn spring-boot:run" comand (without quotes). Maven and jdk should be installed and configured.
2) run the HSQLDB: the database should be downloaded.
server.properties file (placed in C:\hsqldb- 2.6.1\hsqldb) should contain the following properties 
server.database.0 = file:hsqldb/demodb
server.dbname.0 = testdb

Run the following commands in cmd: 
(version 2.6.1 compatable with java 17)
cd C:\hsqldb-2.6.1\hsqldb 
java -classpath lib/hsqldb.jar org.hsqldb.server.Server

java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb

Use cd C:\hsqldb-2.6.1\hsqldb\bin

runManagerSwing.bat
 to run the SQLs inside of the GUI (url jdbc:hsqldb:hsql://localhost/testdb).

3)SQLs:
CREATE TABLE measurments_tbl (
   id INT NOT NULL,
   user_id INT NOT NULL,
   gas INT NOT NULL,
   cold_water INT NOT NULL,
   hot_water INT NOT NULL,
   
   PRIMARY KEY (id) 
);


create index user_id_i on measurments_tbl (user_id);

4)Use Postman or other api testing system to test the application.

For the submit request (POST) use : http://localhost:8080/api/submit and post body for example
{
   "id":3,
   "userId" : 2,
   "gas" : 3,
   "coldWater" : 125,
   "hotWater" : 300
}

For the history request (GET)use http://localhost:8080/api/history/{userId} for example http://localhost:8080/api/history/1
