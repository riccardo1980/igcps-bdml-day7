#!/bin/bash


source 00_configuration.sh

for topic in 'arrived' 'departed' 'wheelsoff'
do 
	gcloud  pubsub topics delete projects/${PROJECT_ID}/topics/$topic
done
