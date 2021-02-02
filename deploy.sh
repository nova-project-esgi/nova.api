#!/usr/bin/env bash

rm -rf build/
echo "Deleted build/ folder"

./gradlew bootJar
echo "Generating jar file"

#Copy execute_commands_on_ec2.sh file which has commands to be executed on server... Here we are copying this file
# every time to automate this process through 'axon-server-deploy.sh' so that whenever that file changes, it's taken care of
scp -i "archi_cloud_api.pem" execute_commands_on_ec2.sh ec2-user@ec2-34-247-34-111.eu-west-1.compute.amazonaws.com:/home/ec2-user
echo "Copied latest 'execute_commands_on_ec2.sh' file from local machine to ec2 instance"

scp -i "archi_cloud_api.pem" bootstrap/build/libs/bootstrap-0.0.1-SNAPSHOT.jar ec2-user@ec2-34-247-34-111.eu-west-1.compute.amazonaws.com:/home/ec2-user
echo "Copied jar file from local machine to ec2 instance"

echo "Connecting to ec2 instance and starting server using java -jar command"
ssh -i "archi_cloud_api.pem" ec2-user@ec2-34-247-34-111.eu-west-1.compute.amazonaws.com bash execute_commands_on_ec2.sh
