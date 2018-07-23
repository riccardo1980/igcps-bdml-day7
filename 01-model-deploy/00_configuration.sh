#!/bin/bash

export BUCKET='telemar-formazione-master-day7'

export REGION='europe-west1'
export MODEL_NAME="flights"
export MODEL_VERSION="v1"
export TF_VERSION="1.6"

export PROJECT_ID=$(gcloud config get-value core/project)

