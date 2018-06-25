#!/bin/sh
echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z database $DATABASESERVER_PORT`; do sleep 3; done
echo "******** Database Server has started "

echo "********************************************************"
echo "Starting bankslip application with profile: $PROFILE at server port: $SERVER_PORT"
echo "********************************************************"
java -Dserver.port=$SERVER_PORT -Dspring.profiles.active=$PROFILE  \
     -jar /usr/local/bankslip/@project.build.finalName@.jar