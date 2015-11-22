package tzamanhadoop.pmf;

//import tauzaman.timestamp.Granule;


/**
* The <code>ProbabilityMassFunction</code> class approximates
* a mass function with two parameters, precision and coarseness, and 
* performs mappings between precision and coarseness.
* 
* A <code>ProbabilityMassFunction</code> represents a mass function given by
* an array of <code>floats</code> that denote the mass at evenly spaced points. So, for
* a mass function array of size <code>V</code>, then the distance between points is
* <I>D = 1/V</I>. The first point is at <I>1/2 * D</I>, the second point
* at <I>3/2 * D</I>, etc. to <I>(2V - 1)/2 * D </I>. The total mass of the
* mass function is scaled to 1.
*  
* The approximation is a sequence of <I>rods</I>, each with probability mass <I>1 / precision</I>.
* These rods are spread across a total domain of <code>coarseness</code> points. The <code>coarseness</code> 
* is rounded up to a power of 2 and is generally much greater than the <code>precision</code>, usually 
* the square of the <code>precision</code>. The higher the precision, the more closely the mass
* function is approximated. 
*
* @author  Curtis Dyreson, Alex Henniges, and Richard Snodgrass
* @version 2.0, Sep/2/2008
* @see     tauzaman.timestamp.Granule
**/
public abstract class ProbabilityMassFunction {
	protected int precision;
	protected int coarseness;
	
	/**
	 * Returns a <code>ProbabilityMassFunction</code> given a
	 * <code>precision</code> and <code>mass function</code>.
	 * The <code>coarseness</code> of the <code>ProbabilityMassFunction</code>
	 * returned is the smallest power of 2 greater than the square of
	 * the precision.
	 * 
	 * This currently returns a <code>ConretePMF</code> only.
	 * 
	 * @param precision
	 * 		The <code>precision</code> of the <code>ProbabilityMassFunction</code> to receive.
	 * @param massFunction
	 * 		An array of <code>floats</code> denoting the mass function to approximate.
	 * @return
	 */
	public static ProbabilityMassFunction getPMF(int precision, float[] massFunction) {
		int coarseness = 1;
		while(coarseness < precision * precision) {
			coarseness *= 2;
		}
		return new ConcretePMF(precision, coarseness, massFunction);
	}
	
	/**
	 * Returns this <code>ProbabilityMassFunction</code>'s total 
	 * precision, as passed in to the constructor.
	 * 
	 * @return The precision of this <code>ProbabilityMassFunction</code>
	 */
	public int getMaxPrecision() {
		return precision;
	}
	
	/**
	 * Returns this <code>ProbabilityMassFunction</code>'s total 
	 * coarseness, as passed in to the constructor.
	 * 
	 * @return The coarseness of this <code>ProbabilityMassFunction</code>
	 */
	public int getMaxCoarseness() {
		return coarseness;
	}
	
	/**
	 * Returns the greatest coarseness point for the rod corresponding to 
	 * the given partial mass. 
	 * 
	 * @param partialMass
	 * 	The amount of mass to the left of the desired coarseness point. Employs a
	 *  0-based indexing, so valid values for partial mass are from 
	 *  <I>0</I> to <I>maxPrecision - 1</I> inclusive. So 0 is <I>0 / P</I>
	 *  mass and 1 is <I>1 / P</I> mass.
	 * @return
	 *  A coarseness point where the mass up to that point
	 *  is equal to <code>partialMass</code>.
	 */
	public abstract int getCoarseness(int partialMass);
	
	/**
	 * Returns the partial mass accumulated from the beginning of the domain 
	 * to the left of the specified coarseness point.
	 * 
	 * @param coarseness
	 * 	The coarseness point whose partial mass is being determined. Employs a 
	 *  0-based indexing, so valid  values for coarseness point are from 
	 *  <I>0</I> to <I> maxCoarseness - 1</I> inclusive.
	 * @return
	 *  Partial mass to the left of the coarseness point as a 0-based index.
	 */
	public abstract int getPrecision(int coarseness);
	
	/**
	 * Used for testing.
	 */
	public static void main(String[] args) {
		float[] massfunctionA = {1, 1, 1, 1, 1, 1, 1, 1};
		float[] massfunctionB = {1, 1, 1, 1, 1, 1, 1, 1};
		float[] massfunctionC = {3, 2, 2, 1, 1, 1, 1, 1};
		ProbabilityMassFunction pmfA = getPMF(64, massfunctionA);
		ProbabilityMassFunction pmfB = getPMF(64, massfunctionB);
		ProbabilityMassFunction pmfC = getPMF(64, massfunctionC);
		
		System.out.println("a = new Granule(1, 3, pmfA)\n"
				+ "b = new Granule(1, 3, pmfB)");
		/*
                Granule a = new Granule(1, 3, pmfA);
		Granule b = new Granule(1, 3, pmfB);
		
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
		
		System.out.println("\na = new Granule(1, 10, pmfA)\n"
				+ "b = new Granule(1, 10, pmfB)");
		a = new Granule(1, 10, pmfA);
		b = new Granule(1, 10, pmfB);
		
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
		
		System.out.println("\na = new Granule(1, 30, pmfA)\n"
				+ "b = new Granule(1, 30, pmfB)");
		a = new Granule(1, 30, pmfA);
		b = new Granule(1, 30, pmfB);
		
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
		
		System.out.println("\na = new Granule(1, 10, pmfA)\n"
				+ "b = new Granule(5, 15, pmfB)");
		a = new Granule(1, 10, pmfA);
		b = new Granule(5, 15, pmfB);
		
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
		
		System.out.println("\na = new Granule(1, 10, pmfA)\n"
				+ "b = new Granule(8, 13, pmfB)");
		a = new Granule(1, 10, pmfA);
		b = new Granule(8, 13, pmfB);
		
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
		
		System.out.println("\na = new Granule(1, 10, pmfC)\n"
				+ "b = new Granule(1, 10, pmfB)");
		a = new Granule(1, 10, pmfC);
		b = new Granule(1, 10, pmfB);
		
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
