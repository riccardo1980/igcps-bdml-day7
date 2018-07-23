#!/bin/bash

source 00_configuration.sh

MODEL_LOCATION=$(gsutil ls gs://${BUCKET}/flights/chapter9/output/Servo/ | tail -1)

echo "Deleting and deploying $MODEL_NAME $MODEL_VERSION from $MODEL_LOCATION..." 

gcloud ml-engine versions delete ${MODEL_VERSION} --model ${MODEL_NAME} --quiet
gcloud ml-engine models delete ${MODEL_NAME} --quiet
gcloud ml-engine models create ${MODEL_NAME} --regions $REGION
gcloud ml-engine versions create ${MODEL_VERSION} --model ${MODEL_NAME} --origin ${MODEL_LOCATION} --runtime-version=${TF_VERSION}