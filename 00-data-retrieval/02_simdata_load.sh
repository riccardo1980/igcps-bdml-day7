#!/bin/bash


source 00_configuration.sh

FROM=gs://telemar-formazione-master-day6-solution

bq mk $BQ_DATASET

echo "Loading all files into BigQuery: $BQ_DATASET.simevents"

gsutil cp $FROM/simevents.json simevents.json
bq load --skip_leading_rows 1 $BQ_DATASET.simevents "$FROM/simevents/simevents*" ./simevents.json