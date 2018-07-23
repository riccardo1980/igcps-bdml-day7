cd df-java-streaming

set BUCKET=telemar-formazione-master-day7
set PROJECT=telemar-formazione-master

mvn compile exec:java ^
	-Dexec.mainClass=it.injenia.formazione.gcp.dataplatform.dataflow.school.AddRealtimePrediction ^
 	-Dexec.args="--runner=DataflowRunner --realtime --speedupFactor=60 --maxNumWorkers=10 --autoscalingAlgorithm=THROUGHPUT_BASED --bucket=%BUCKET% --project=%PROJECT%"