#!/bin/bash

export BUCKET='telemar-formazione-master-day7'
export BQ_DATASET='telemar_formazione_master_day7'
export REGION='europe-west3'

export PROJECT_ID=$(gcloud config get-value core/project)