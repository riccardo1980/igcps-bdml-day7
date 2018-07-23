cd df-java-batch

set BUCKET=telemar-formazione-master-day7
set PROJECT=telemar-formazione-master

mvn compile exec:java ^
	-Dexec.mainClass=it.injenia.formazione.gcp.dataplatform.dataflow.school.AddRealtimePrediction ^
 	-Dexec.args="--runner=DirectRunner --speedupFactor=60 --bucket=%BUCKET% --project=%PROJECT%"