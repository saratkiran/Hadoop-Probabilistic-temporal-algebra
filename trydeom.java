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

// trying the joins in mapreduce.


/*public class DblArrayWritable extends ArrayWritable 
{ 
    public DblArrayWritable() 
    { 
        super(DoubleWritable.class); 
    }
}
public class MyMap extends Mapper<LongWritable, Text, Text, DblArrayWritable> 
{
  public void map(LongWritable key, Text value, Context context) throws IOException 
  {

    String line = value.toString();
    String[] attribute=line.split(",");
    DoubleWritable[] values = new DoubleWritable[2];
    values[0] = Double.parseDouble(attribute[14]);
    values[1] = Double.parseDouble(attribute[17]);

    String comb=new String();
    comb=attribute[5].concat(attribute[8].concat(attribute[10]));

    context.write(new Text(comb),new DblArrayWritable.set(values));

  }
}*/
public class trydeom {
    
}
