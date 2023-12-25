to run this application 
1) open command promt and go to the monitoring-app directory and run "mvn spring-boot:run" comand (without quotes)
2) run the HSQLDB: the database should be downloaded. 

Run the following commands in cmd: 

cd C:\hsqldb-2.6.1\hsqldb
java -classpath lib/hsqldb.jar org.hsqldb.server.Server

java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb


3)Use Postman or other api testing system to test the application.

For the submit request use : http://localhost:8080/api/submit and post body for example
{
   "id":3,
   "userId" : 2,
   "gas" : 3,
   "coldWater" : 125,
   "hotWater" : 300
}

For the history request use http://localhost:8080/api/history/{userId} for example http://localhost:8080/api/history/1
