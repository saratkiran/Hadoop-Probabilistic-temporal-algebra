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
 
// For each rod number we compare the start date and end dates of each rod and calculate the values by comparing it 
//with the same rod of other event and note the number of rods in front of it (we can get it by comparing the start and 
//end time of rods). After we have these values we emit into the reduce function with key as each comparison
//(event1 > event2, event1 <event2, ...) and value as calculated.
public class secondmapper extends Mapper<LongWritable, Text, Text, LongWritable> {
  private Text word = new Text();
  private LongWritable count = new LongWritable();
 
  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String[] split = value.toString().split("\t+");
    int rodno = Integer.parseInt(split[0]);
    int j= 0;
    // as each rod number of different events are present in a single row and each rod has 4 informations we 
    // iterate through the for loop with i +=4 and comparing the start and end time
    for(int i=3;i<split.length;){
        j =i+4;
        if(j<split.length){
        String worder = split[i]+" > "+split[j];
        int temp= Integer.parseInt(split[i-1]); // end time of one rod
        int temp2 = Integer.parseInt(split[j-2]); // start time of another rod
        // checking the start/end time
        if(temp - temp2 < 0){
            int settemp = Integer.parseInt(split[j+1]);
            
            count.set(settemp - rodno);
        }
         else
            count.set(0);
        word.set(worder);
        context.write(word, count);
        
        String worder2 = split[j]+ " > " + split[i];
        int temp3 = Integer.parseInt(split[j-1]);  // end time of one rod
        int temp4 = Integer.parseInt(split[i-2]);   // start time of another rod
        if(temp3- temp4 < 0){
            int settemp2 = Integer.parseInt(split[i+1]);
            count.set(settemp2-rodno);
            
        }
        else
            count.set(0);
        word.set(worder2);
        context.write(word,count);
        
        }
        i= i+4;
        
        
    }
    
    
    }
  /*
    int i =2;
    word.set(split[3]);
    if (split.length > 1) {
      
          while(i<split.length){
              
        //count.set(Long.parseLong(split[3]));
          count.set(split[i]);
        context.write(word, count);
        i+=4;
          }
      } catch (NumberFormatException e) {
        // cannot parse - ignore
      }
    }
  } */
}
