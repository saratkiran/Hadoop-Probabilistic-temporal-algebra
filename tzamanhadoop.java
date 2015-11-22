/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzamanhadoop;

/**
 *
 * @author hduser
 */ 
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
 
public class tzamanhadoop extends Configured implements Tool {
 
  @Override
  public int run(String[] args) throws Exception {
    Job job = new Job(getConf());
    job.setJarByClass(getClass());
    job.setJobName(getClass().getSimpleName());
 
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    // First map reduce is used to generate files that are used on second mapreduce job
    //job.setMapperClass(mapper.class);
    //job.setCombinerClass(reducer.class);
    //job.setReducerClass(reducer.class)
            
    job.setMapperClass(secondmapper.class);
    job.setCombinerClass(scondreducer.class);
    job.setReducerClass(scondreducer.class);
 
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);
 
    return job.waitForCompletion(true) ? 0 : 1;
  }
 
  public static void main(String[] args) throws Exception {
    int rc = ToolRunner.run(new tzamanhadoop(), args);
    System.exit(rc);
  }
}