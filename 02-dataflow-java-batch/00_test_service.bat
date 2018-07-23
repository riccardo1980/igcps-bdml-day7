cd df-java-batch

mvn compile exec:java ^
	-Dexec.mainClass=it.injenia.formazione.gcp.dataplatform.dataflow.school.FlightsMLService