#!/bin/bash
warVersion=1.0-SNAPSHOT	
rm  /home/mdominguez/desarrollos/deploys/*
mvn clean install
cp target/relevamientos-infraestructura-$warVersion.war /home/mdominguez/desarrollos/deploys/relevamientos-infraestructura.war
/home/mdominguez/glassfish3/bin/asadmin undeploy relevamientos-infraestructura
/home/mdominguez/glassfish3/bin/asadmin deploy /home/mdominguez/desarrollos/deploys/relevamientos-infraestructura.war
#mvn clean
echo "Listo!!!"