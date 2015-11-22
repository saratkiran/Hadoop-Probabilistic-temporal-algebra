/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzamanhadoop;

/**
 *
 * @author hduser
 */
import java.util.StringTokenizer;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 /**
 *
 * @author hduser
 */
// 4. Here we add the values emitted from the second mapreduce map step and add them. 
 
public class scondreducer<KEY> extends Reducer<KEY, LongWritable,
                                                 KEY,LongWritable> {
 
  private LongWritable result = new LongWritable();
 
  public void reduce(KEY key, Iterable<LongWritable> values,
                     Context context) throws IOException, InterruptedException {
    long sum = 0;
    for (LongWritable val : values) {
      sum += val.get(); // add the values and emit
    }
    result.set(sum);
    context.write(key, result);
  }
}
 