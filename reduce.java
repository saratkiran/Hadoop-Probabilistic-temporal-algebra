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
 // 2.  we now have the aggregated information of each rod  of different events, we use this in our next mapreduce job map function call.
public class reduce extends Reducer<Text, Text,Text,Text> {
 
  private Text result = new Text();
 
  @Override
  public void reduce(Text key, Iterable<Text> values,
                     Context context) throws IOException, InterruptedException {
    //long sum = 0;
    //for (LongWritable val : values) {
     // sum += val.get();
    //}
    //result.set(values);
      String translations = "";
     
	 int count =0;
        for (Text val : values) {
                    count++;
                    // nothing much here just send the string to next mapreduce job
                    translations +=  val.toString() ;
                     translations += "\t";
            
            /*StringTokenizer itr = new StringTokenizer(val.toString(),"\t");
            while (itr.hasMoreTokens())
                {
                    translations += "|" + itr.nextToken();
                }
                   String[] spliter = val.toString().split("\\s");
                   //if(spliter.length > 0)
                   //translations += "|" + spliter[2];
                   
                   translations = Integer.toString(spliter.length);
                   
                    for (int x=0; x<spliter.length; x++){
                     
	            translations += "|" + spliter[x] + "  " + x ;
                       
                    }
                    */
                    
	        }
       
	 
       result.set(translations);
    context.write(key, result);
  }
}
 