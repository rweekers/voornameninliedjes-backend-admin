rm D:/Tools/jetty-distribution-8.1.15.v20140411/webapps/namesandsongs.war
cp target/namesandsongs.war D:/Tools/jetty-distribution-8.1.15.v20140411/webapps
cd $JETTY_HOME
java -jar start.jar
