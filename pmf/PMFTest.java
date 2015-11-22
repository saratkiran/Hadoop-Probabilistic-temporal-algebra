package tzamanhadoop.pmf;

//import tauzaman.timestamp.Granule;
import java.*;
import java.lang.String;
import java.io.FileWriter;
import java.io.IOException;

public class PMFTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float[] massfunctionB = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		float[] massfunctionA = {5, 4, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		ProbabilityMassFunction pmfB = ProbabilityMassFunction.getPMF(256, massfunctionB);
		ProbabilityMassFunction pmfA = ProbabilityMassFunction.getPMF(256, massfunctionA);
				
		System.out.println("\na = new Granule(1, 10, pmfA)\n"
				+ "b = new Granule(1, 10, pmfB)");
                
                int[][] values = new int[3][10];
                int end=0;
                try{
                    FileWriter writer = new FileWriter("test.csv");
                    
                    for(int i =0;i<10;i++){
                     
                     int temp = pmfA.getCoarseness(i);
                     
                    writer.append(String.valueOf(end)+","+String.valueOf(temp));
                    writer.append(';');
                     writer.flush();
                     end = temp+1;
                    } 
                    writer.append("\n");
                    end = 0;
                    for(int i =0;i<10;i++){
                     int temp = pmfB.getCoarseness(i);
                    writer.append(String.valueOf(end)+","+String.valueOf(temp));
                    writer.append(';');
                     writer.flush();
                     end = temp+1;
                    }
                    
                    
            
            writer.close();
 

                }
                catch(IOException e)
                {
                 e.printStackTrace();
                } 
                
                
                
                /*	double[,] data = new double[10000, 650];
                using (StreamWriter outfile = new StreamWriter(@"C:\Temp\test.csv"))
   {
      for (int x = 0; x < 10000; x++)
      {
         string content = "";
         for (int y = 0; y < 650; y++)
         {
            content += data[x, y].ToString("0.00") + ";";
         }
         outfile.WriteLine(content);
      }
   }
                 * */
                 
                System.out.println(pmfB.getCoarseness(0));
                System.out.println(pmfA.getCoarseness(0));
                System.out.println(pmfA.getCoarseness(11));
                
                System.out.println(pmfA.getCoarseness(256));
                //System.out.println( a.getGranularity());
                /*
                Granule a = new Granule(1, 30, pmfA);
		Granule b = new Granule(1, 20, pmfB);
                
               
		
		System.out.println("a < b (50): " + a.lessThan(b, 50));
		System.out.println("b < a (50): " + b.lessThan(a, 50));
		
		System.out.println("a < b (30): " + a.lessThan(b, 30));
		System.out.println("b < a (30): " + b.lessThan(a, 30));
		
		System.out.println("a < b (10): " + a.lessThan(b, 10));
		System.out.println("b < a (10): " + b.lessThan(a, 10));
		
		System.out.println("a < b (80): " + a.lessThan(b, 80));
		System.out.println("b < a (80): " + b.lessThan(a, 80));
		
		System.out.println("a < b (95): " + a.lessThan(b, 95));
		System.out.println("b < a (95): " + b.lessThan(a, 95));	
                 
                 */

	}

}
