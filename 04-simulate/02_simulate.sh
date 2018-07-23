#!/bin/bash

source 00_configuration.sh

python simulate.py --project $PROJECT_ID --dataset $BQ_DATASET --startTime "2015-04-01 00:00:00 UTC" --endTime "2015-04-03 00:00:00 UTC" --speedFactor 60 --jitter=None
