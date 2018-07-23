package it.injenia.formazione.gcp.dataplatform.dataflow.school;

import java.text.DecimalFormat;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.services.bigquery.model.TableRow;
import it.injenia.formazione.gcp.dataplatform.dataflow.school.AddRealtimePrediction.MyOptions;

@SuppressWarnings("serial")
public class BatchInputOutput extends InputOutput {
  private static final Logger LOG = LoggerFactory.getLogger(InputOutput.class);
  
  private static String getOutput(MyOptions opts) {
    return "gs://BUCKET/flights/chapter10/output/".replace("BUCKET", opts.getBucket());
  }
  
  @SuppressWarnings("deprecation")
  @Override
  public PCollection<Flight> readFlights(Pipeline p, MyOptions options) {
    String query = "SELECT EVENT_DATA FROM flights.simevents WHERE ";
    query += " STRING(FL_DATE) = '2015-01-04' AND ";
    query += " (EVENT = 'wheelsoff' OR EVENT = 'arrived') ";
    query += " LIMIT 1000 ";
    LOG.info(query);
    
    // HANDS ON: insert your solution here
    
  }

  @Override
  public void writeFlights(PCollection<Flight> outFlights, MyOptions options) {
	
	// HANDS ON: insert your solution here  

  }

  private PCollection<String> predToCsv(PCollection<FlightPred> preds) {
    return preds.apply("pred->csv", ParDo.of(new DoFn<FlightPred, String>() {
      @ProcessElement
      public void processElement(ProcessContext c) throws Exception {
        FlightPred pred = c.element();
        String csv = String.join(",", pred.flight.getFields());
        if (pred.ontime >= 0) {
          csv = csv + "," + new DecimalFormat("0.00").format(pred.ontime);
        } else {
          csv = csv + ","; // empty string -> null
        }
        c.output(csv);
      }})) //
;
  }
}
