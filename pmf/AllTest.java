package tzamanhadoop.pmf;

import java.util.Random;
import java.lang.management.*;
//import tauzaman.extendedboolean.ExtendedBoolean;
//import tauzaman.extendedboolean.ExtendedBoolean.LogicValue;
//import tauzaman.timestamp.Granule;

public class AllTest {

    /** Get CPU time in nanoseconds. */
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
                ? bean.getCurrentThreadCpuTime() : 0L;
    }

    /** Get user time in nanoseconds. */
    public static long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
                ? bean.getCurrentThreadUserTime() : 0L;
    }

    /** Get system time in nanoseconds. */
    public static long getSystemTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
                ? (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime()) : 0L;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(100) + 1;
        float[] massfunctionB = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        float[] massfunctionA = {5, 4, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        ProbabilityMassFunction pmfB = ProbabilityMassFunction.getPMF(256, massfunctionB);
        ProbabilityMassFunction pmfA = ProbabilityMassFunction.getPMF(256, massfunctionA);

        System.out.println("\na = new Granule(1, 10, pmfA)\n"
                + "b = new Granule(1, 10, pmfB)");
        //Granule a = new Granule(1, 10, pmfA);
        //Granule a = new Granule(randomIndex, 100, pmfA);
        //Granule b = new Granule(1, randomIndex, pmfB);
        long startSystemTimeNano = getSystemTime();
        long startUserTimeNano = getUserTime();
        long startCpuTimeNano = getCpuTime();
        /*
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
        /*
        ExtendedBoolean x = tauzaman.extendedboolean.ExtendedBoolean.TrueEB;
        for (int i = 1; i < 1000000; i++) {
        x = a.lessThan(b, 50);
        b.lessThan(a, 50);

       a.lessThan(b, 30);
        b.lessThan(a, 30);

        a.lessThan(b, 10);
      b.lessThan(a, 10);

         a.lessThan(b, 80);
       b.lessThan(a, 80);

        a.lessThan(b, 95);
        b.lessThan(a, 95);
        } */
        long taskUserTimeNano = getUserTime() - startUserTimeNano;
        long taskSystemTimeNano = getSystemTime() - startSystemTimeNano;
        long taskCpuTimeNano = getCpuTime() - startCpuTimeNano;
        System.out.println("userTime " + taskUserTimeNano);
        System.out.println("systemTime " + taskSystemTimeNano);
        System.out.println("cpuTime " + taskCpuTimeNano);
        //System.out.println("x is " + x);

    }
}
