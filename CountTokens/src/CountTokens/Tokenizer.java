package counttokens;

import com.continuuity.api.flow.flowlet.AbstractComputeFlowlet;
import com.continuuity.api.flow.flowlet.OutputCollector;
import com.continuuity.api.flow.flowlet.StreamsConfigurator;
import com.continuuity.api.flow.flowlet.Tuple;
import com.continuuity.api.flow.flowlet.TupleContext;
import com.continuuity.api.flow.flowlet.TupleSchema;
import com.continuuity.flow.flowlet.internal.TupleBuilderImpl;
import com.continuuity.flow.flowlet.internal.TupleSchemaBuilderImpl;

public class Tokenizer extends AbstractComputeFlowlet {

  @Override
  public void configure(StreamsConfigurator configurator) {
    TupleSchema in = new TupleSchemaBuilderImpl().
        add("title", String.class).
        add("text", String.class).
        create();
    configurator.getDefaultTupleInputStream().setSchema(in);

    TupleSchema out = new TupleSchemaBuilderImpl().
        add("field", String.class).
        add("word", String.class).
        create();
    configurator.getDefaultTupleOutputStream().setSchema(out);
  }

  @Override
  public void process(Tuple tuple, TupleContext tupleContext, OutputCollector outputCollector) {
    final String[] fields = { "title", "text" };

    if (Common.debug)
      System.out.println(this.getClass().getSimpleName() + ": Received tuple " + tuple);

    for (String field : fields)
      tokenize((String)tuple.get(field), field, outputCollector);
  }

  void tokenize(String str, String field, OutputCollector outputCollector) {
    if (str == null) return;
    final String delimiters = "[ .-]";
    String[] tokens = str.split(delimiters);

    for (String token : tokens) {

      Tuple output = new TupleBuilderImpl().
          set("field", field).
          set("word", token).
          create();

      if (Common.debug)
        System.out.println(this.getClass().getSimpleName() + ": Emitting tuple " + output);

      outputCollector.emit(output);
    }
  }

}
