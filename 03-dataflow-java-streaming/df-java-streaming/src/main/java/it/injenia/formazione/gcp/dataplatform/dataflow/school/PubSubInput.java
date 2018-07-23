package it.injenia.formazione.gcp.dataplatform.dataflow.school;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;

import it.injenia.formazione.gcp.dataplatform.dataflow.school.AddRealtimePrediction.MyOptions;

@SuppressWarnings("serial")
public abstract class PubSubInput extends InputOutput {

  @Override
  public PCollection<Flight> readFlights(Pipeline p, MyOptions options) {
    // return readFlights_flatten(p, options);
    return readFlights_workaround(p, options);
  }

  // cleaner implementation, but doesn't work because of b/34884809
  @SuppressWarnings("unused")
  private PCollection<Flight> readFlights_flatten(Pipeline p, MyOptions options) {
    // empty collection to start
    PCollectionList<Flight> pcs = PCollectionList.empty(p);
    // read flights from each of two topics
    for (String eventType : new String[]{"wheelsoff", "arrived"}){
      String topic = "projects/" + options.getProject() + "/topics/" + eventType;
      PCollection<Flight> flights = p.apply(eventType + ":read",
          PubsubIO.readStrings().fromTopic(topic).withTimestampAttribute("EventTimeStamp")) //
          .apply(eventType + ":parse", ParDo.of(new DoFn<String, Flight>() {
            @ProcessElement
            public void processElement(ProcessContext c) throws Exception {
              String line = c.element();
              Flight f = Flight.fromCsv(line);
              if (f != null) {
                c.output(f);
              }
            }
          }));
      pcs = pcs.and(flights);
    }
    // flatten collection
    return pcs.apply(Flatten.<Flight>pCollections());
  }

  // workaround  b/34884809 by streaming to a new topic and reading from it.
  public PCollection<Flight> readFlights_workaround(Pipeline p, MyOptions options) {
    String tempTopic = "projects/" + options.getProject() + "/topics/dataflow_temp";
    // HANDS ON: insert your solution here



  } 
}
