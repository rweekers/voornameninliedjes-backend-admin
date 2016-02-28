# README #

This README desbribes the steps to install the application and get it running. 

### Create the database ###

* Clone the voornameninliedjes-database project
* Create a database
* Import the basic.sql database

### Config files ###

Create a file database.properties in the project root

Fill the file with the following properties:

driver=<Database driver, for Postgresql org.postgresql.Driver>
url=<url of your database, including port>
username=<user of your database>
password=<password for your user>

Create a file config.properties in the project root

Fill the following properties:

user=<admin user>
password=<admin password>

### Install and run ###

Install the application with the following command:

mvn clean install

and run with

mvn jetty:run

The REST services can be found on localhost:8080/namesandsongs/api/<service>
