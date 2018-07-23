#!/bin/bash

source 00_configuration.sh

FROM=gs://data-science-on-gcp/flights/chapter8/output
TO=gs://$BUCKET/flights/chapter8/output

CMD="gsutil -m cp "

CMD="$CMD ${FROM}/delays.csv $TO/delays.csv"

echo $CMD
$CMD
