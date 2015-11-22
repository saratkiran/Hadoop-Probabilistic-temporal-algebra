/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzamanhadoop;

/**
 *
 * @author hduser
 */
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
import java.io.IOException;
//map : collects the same number rod for all the events and pass to the reduce step
public class mapper extends Mapper<LongWritable, Text, Text, Text> {
    
  private Text word = new Text(); //have the key - rod no
  private Text count = new Text(); // have the ron info
 
  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    // 
    String[] split = value.toString().split("\t+");
    word.set(split[3]); // split the key from the list generated from PMF file
    if (split.length > 1) {
      try {
        //count.set(Long.parseLong(split[3]));
          count.set(split[0] +"\t"+split[1]+"\t"+split[2]+"\t"+split[4]);
        context.write(word, count);
      } catch (NumberFormatException e) {
        // cannot parse - ignore
      }
    }
  }
}
