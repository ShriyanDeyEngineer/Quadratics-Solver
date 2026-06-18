import java.util.ArrayList;
import java.math.*;

public class SolvingMethods 
{
	
	private double a;
	private double b;
	private double c;
	
	private ArrayList<Integer> potentialFactors;
	private ArrayList<Integer> confirmedFactors;
	
	// Constructor method to create an object that holds the user's given quadratic expression
	public SolvingMethods(double xSquaredCoefficient, double xCoefficient, double onesTerm)
	{
		a = xSquaredCoefficient;
		b = xCoefficient;
		c = onesTerm;
		
		potentialFactors = new ArrayList<Integer>();
		confirmedFactors = new ArrayList<Integer>();
	}
	
	// Method to do the first step of the diamond problem strategy
	private ArrayList<Integer> findPotentialFactors(double a, double b, double c)
	{	
		System.out.println("");
		System.out.println("Multiplying a * c for the number at the top of the diamond problem. Putting the b term on the bottom.");
		System.out.println("");
		System.out.println("Calculating potential solutions to the diamond problem by looking for factors that multiply to (a * c) and sum to b...");
		System.out.println("");
		
		double topSquare = (a) * (c);
		double loopCount;
		
		double findingMax = Math.max(Math.abs(a), Math.abs(b));
		loopCount = Math.max(Math.abs(c), findingMax);
		
		for(int i = 1; i <= loopCount; i++)
		{
			if(Math.abs(topSquare) % i == 0)
			{
				potentialFactors.add(i);
				potentialFactors.add(i * (-1));
			}
		}
		
		System.out.println("Potential solutions to the diamond problem for this quadratic expression are: " + potentialFactors);
		
		return potentialFactors;
	}
	
	// Method to do the second step of the diamond problem strategy
	private ArrayList<Integer> solveDiamondProblem(double a, double b, double c)
	{	
		System.out.println("");
		System.out.println("Scanning for appropriate solutions from the potential solutions list...");
		
		double topSquare = (a) * (c);
		double bottomSquare = b;
		
		for(int i = 0; i < potentialFactors.size(); i++)
		{
			for(int j = 0; j < potentialFactors.size(); j++)
			{
				if(potentialFactors.get(i) * potentialFactors.get(j) == topSquare && potentialFactors.get(i) + potentialFactors.get(j) == bottomSquare)
				{
					confirmedFactors.add(potentialFactors.get(i));
					confirmedFactors.add(potentialFactors.get(j));
					
					if(confirmedFactors.size() == 2)
					{
						System.out.println("Diamond problem solved. These are the two confirmed values: " + confirmedFactors);
						return confirmedFactors;
					}
				}
			}
		}
		
		if(confirmedFactors.size() != 2)
		{
			return null;
		}
	
		return confirmedFactors;
	}
	
	// Method to do the third step of the diamond problem strategy
	private void showFactorRectangle(double a, double b, double c)
	{
		System.out.println("");
		System.out.println("Generating factor rectangle...");
		
		double[][] factorRectangle = new double[2][2];
		
		factorRectangle[0][0] = a;
		factorRectangle[0][1] = confirmedFactors.get(0);
		factorRectangle[1][0] = confirmedFactors.get(1);
		factorRectangle[1][1] = c;
		
		System.out.println("Factor rectangle completed (Find the appropriate integers that multiply into each slot and you will see the factors to your quadratic expression. Set those factors = to 0 and solve for x to obatain the solutions to your quadratic expression):");
		
		// Print out the factor rectangle that the user can use to find the factors of their given quadratic expression
		System.out.println("+---------------+--------------+");
	    System.out.printf("| %10.3fx^2 | %10.3fx  |\n", factorRectangle[0][0], factorRectangle[0][1]);
	    System.out.println("+---------------+--------------+");
	    System.out.printf("| %10.3fx   | %10.3f   |\n", factorRectangle[1][0], factorRectangle[1][1]);
	    System.out.println("+---------------+--------------+");
	}
	
	// Method that combines the methods that do the three steps of the diamond problem strategy
	public void diamondProblemMethod()
	{
		findPotentialFactors(a, b, c);
		potentialFactors = solveDiamondProblem(a, b, c);
		
		if(potentialFactors == null)
		{
			System.out.println("");
			System.out.println("Womp womp, this quadratic expression is not factorable. Try another way.");
			System.out.println("");
		}
		else
		{
			System.out.println("");
			showFactorRectangle(a, b, c);
			System.out.println("");
		}
	}
	
	// Method to do the completing the square strategy
	public void completingTheSquare(double a, double b, double c)
	{
		double[][] completingTheSquareBox = new double[2][2];
		
		System.out.println("Dividing the a and b terms by the a term...");
		completingTheSquareBox[0][0] = (double)a / a;
		double new_b = (double)b / a;
		
		System.out.println("");
		System.out.println("Slashing the new b term into two equal values...");
		completingTheSquareBox[0][1] = new_b / 2;
		completingTheSquareBox[1][0] = new_b / 2;
		System.out.println("");
		
		System.out.println("Filling in the square...");
		completingTheSquareBox[1][1] = completingTheSquareBox[0][1] * completingTheSquareBox[1][0];
		System.out.println("");
		
		System.out.println("Generating the square...");
		System.out.println("+---------------+--------------+");
	    System.out.printf("| %10.3fx^2 | %10.3fx  |\n", completingTheSquareBox[0][0], completingTheSquareBox[0][1]);
	    System.out.println("+---------------+--------------+");
	    System.out.printf("| %10.3fx   | %10.3f   |\n", completingTheSquareBox[1][0], completingTheSquareBox[1][1]);
	    System.out.println("+---------------+--------------+");
	    System.out.println("");
	    
	    System.out.println("Finding the difference in ones between this completed square and the given quadratic expression...");
	    double difference = Math.abs(a * completingTheSquareBox[1][1] - c);
	    double finalOnes = c - (b * b) / (4.0 * a);
	    System.out.println("");
	    
	    System.out.println("Multiplying the a term back in and generating the completed square formula for the given quadratic expression (set = to 0 and solve for x and also, you can find the vertex point from this):");
	    System.out.println(a + "(x + " + new_b / 2 + ")^2 + " + finalOnes);
	}
	
	// Method to do the quadratic formula strategy
	public Object quadraticFormula(double a, double b, double c)
	{	
		System.out.println("Calculating solutions via the quadratic formula...");
		System.out.println("");
		System.out.println("-b +/- √(b^2 - 4ac)");
		System.out.println("-------------------");
		System.out.println("         2a        ");
		System.out.println("");
		
		System.out.println("Simplifying solutions...");
		if(simplifyDiscriminant(a, b, c))
		{
			if(b * b - 4 * a * c == 0 && b == (int)b)
			{
				double a2 = a;
				double b2 = b;
				
				a2 = 2 * a2;
				double numerator = b2;
				double denominator = a2;
				while (b2 != 0) 
				{
		            int temp = (int)b2;
		            b2 = a2 % b2;
		            a2 = temp;
		        }
		        int GCF = (int)a2;
		        
		        numerator /= GCF;
		        denominator /= GCF;
		        
		        System.out.println(String.format(" %.2f ", -numerator));
			    System.out.println(String.format("------------------"));
			    System.out.println(String.format(" %.2f", denominator));
			    System.out.println("");
		        System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
			}
			
			System.out.println("");
			System.out.println("Now calculating decimal solutions to the given quadratic expression...");
			System.out.println("");
		}
		else return null;
		
		double solution1Numerator = ((-1) * (b) + Math.sqrt((b * b) - 4 * (a) * (c)));
		double solution2Numerator = ((-1) * (b) - Math.sqrt((b * b) - 4 * (a) * (c)));
		double solution1 = solution1Numerator / (2 * a);
		double solution2 = solution2Numerator / (2 * a);
		
		if(solution1 == solution2)
		{
			System.out.println("The decimal solutions to the given quadratic expression are: x = " + solution1);
			System.out.println("");
		}
		else
		{
			System.out.println("The decimal solutions to the given quadratic expression are: x = " + solution1 + " and x = " + solution2);
			System.out.println("");
		}
		
		return null;
	}
	
	// Method to simplify the discriminant for a given quadratic expression in the quadratic formula
	private boolean simplifyDiscriminant(double a, double b, double c) {
	    System.out.println();

	    double rawDiscriminant = b * b - 4 * a * c;

	    if (rawDiscriminant < 0) 
	    {
	        System.out.println("No real solutions (however, there are imaginary roots...)");
	        return false;
	    }

	    int discriminant = (int) rawDiscriminant; // Integer version for simplification
	    System.out.println("Simplifying the discriminant (√(b^2 - 4ac))...");
	    System.out.println();
	    System.out.println("Discriminant: " + discriminant);
	    System.out.println();

	    int outsideRoot = 1;
	    int insideRoot = discriminant;

	    // Simplify √discriminant by factoring out squares
	    for (int i = 2; i * i <= insideRoot; i++) 
	    {
	        while (insideRoot % (i * i) == 0) 
	        {
	            insideRoot /= i * i;
	            outsideRoot *= i;
	        }
	    }

	    // Display simplified square root
	    System.out.print("√" + discriminant + " simplifies to: ");
	    if (insideRoot == 1) 
	    {
	        // Case: perfect square
	        System.out.println(outsideRoot);
	        
	        System.out.println("");
		    System.out.println("Solutions for x:");
	        
	        // Pure number, no root
	        System.out.println(String.format(" %.2f +/- %.2f ", -b, (double)outsideRoot));
	        
	        // Denominator line
		    System.out.println(String.format("------------------"));
		    System.out.println(String.format("      %.2f", 2 * a));
		    System.out.println("");
		    
		    // Checking if the overall solution can be further simplified
		    if(simplifyOverallSolution((-1) * b, outsideRoot, 2 * a) && b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
		    }
		    else if(b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
		    }
	    } 
	    else if (outsideRoot == 1) 
	    {
	        // Case: no square factor
	        System.out.println("√" + insideRoot);
	        
	        System.out.println("");
		    System.out.println("Solutions for x:");
	        
	        // Root only, no outside multiplier
	        System.out.println(String.format(" %.2f +/- √%d", -b, insideRoot));
	        
	        // Denominator line
		    System.out.println(String.format("------------------"));
		    System.out.println(String.format("      %.2f", 2 * a));
		    System.out.println("");
		    
		    // Checking if the overall solution can be further simplified
		    if(simplifyOverallSolution((-1) * b, outsideRoot, 2 * a, insideRoot, 0) && b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
		    }
		    else if(b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
	    	}
	    } 
	    else 
	    {
	        // Case: simplified form a√b
	        System.out.println(outsideRoot + "√" + insideRoot);
	        
	        System.out.println("");
		    System.out.println("Solutions for x:");
	        
	        // Full simplified root
	        System.out.println(String.format(" %.2f +/- %d√%d", -b, outsideRoot, insideRoot));
	        
	        // Denominator line
		    System.out.println(String.format("------------------"));
		    System.out.println(String.format("      %.2f", 2 * a));
		    System.out.println();
		    
		    // Checking if the overall solution can be further simplified
		    if(simplifyOverallSolution((-1) * b, outsideRoot, 2 * a, insideRoot) && b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
		    }
		    else if(b * b - 4 * a * c != 0)
		    {
		    	System.out.println("^^^^^^^ Those are the simplified solutions for x (you may be able to rewrite them)^^^^^^^");
	    	}
	    }

	    return true;
	}
	
	// Method to simplify the solutions to x
	private boolean simplifyOverallSolution(double num1, double num2, double num3)
	{
		int timesNumSimplified = 0;
		int potentialCommonFactorsChecked = 2;
		double findingMaxLoop = Math.min(Math.abs(num1), Math.abs(num2));
		double maxLoop = Math.min(Math.abs(num3), findingMaxLoop);
		
		while(potentialCommonFactorsChecked <= maxLoop)
		{
			if(num1 % potentialCommonFactorsChecked == 0 && num2 % potentialCommonFactorsChecked == 0 && num3 % potentialCommonFactorsChecked == 0)
			{
				timesNumSimplified += 1;
				
				num1 /= potentialCommonFactorsChecked;
				num2 /= potentialCommonFactorsChecked;
				num3 /= potentialCommonFactorsChecked;
				
				potentialCommonFactorsChecked = 1;
			}
			
			else if(potentialCommonFactorsChecked >= maxLoop && timesNumSimplified == 0)
			{
				return false;
			}
			
			potentialCommonFactorsChecked += 1;
		}
		
		// Full simplified root
        System.out.println(String.format(" %.2f +/- %.2f ", num1, num2));
        
        // Denominator line
	    System.out.println(String.format("------------------"));
	    System.out.println(String.format("      %.2f", num3));
	    System.out.println("");
		
		return true;
	}
	
	
	// Override method to simplify the solutions to x
	private boolean simplifyOverallSolution(double num1, double num2, double num3, int rootNum, int i)
	{
		int timesNumSimplified = 0;
		int potentialCommonFactorsChecked = 2;
		double findingMaxLoop = Math.min(Math.abs(num1), Math.abs(num2));
		double maxLoop = Math.min(Math.abs(num3), findingMaxLoop);
		
		while(potentialCommonFactorsChecked <= maxLoop)
		{
			if(num1 % potentialCommonFactorsChecked == 0 && num2 % potentialCommonFactorsChecked == 0 && num3 % potentialCommonFactorsChecked == 0)
			{
				timesNumSimplified += 1;
				
				num1 /= potentialCommonFactorsChecked;
				num2 /= potentialCommonFactorsChecked;
				num3 /= potentialCommonFactorsChecked;
				
				potentialCommonFactorsChecked = 1;
			}
			
			else if(potentialCommonFactorsChecked >= maxLoop && timesNumSimplified == 0)
			{
				return false;
			}
			
			potentialCommonFactorsChecked += 1;
		}
		
		// Full simplified root
		System.out.println(String.format(" %.2f +/- √%d", num1, rootNum));
        
        // Denominator line
	    System.out.println(String.format("------------------"));
	    System.out.println(String.format("      %.2f", num3));
	    System.out.println("");
		
		return true;
	}
	
	// Override method to simplify the solutions to x
	private boolean simplifyOverallSolution(double num1, int num2, double num3, int insideRoot)
	{
		int timesNumSimplified = 0;
		int potentialCommonFactorsChecked = 2;
		double findingMaxLoop = Math.min(Math.abs(num1), Math.abs(num2));
		double maxLoop = Math.min(Math.abs(num3), findingMaxLoop);
		
		while(potentialCommonFactorsChecked <= maxLoop)
		{
			if(num1 % potentialCommonFactorsChecked == 0 && num2 % potentialCommonFactorsChecked == 0 && num3 % potentialCommonFactorsChecked == 0)
			{
				timesNumSimplified += 1;
				
				num1 /= potentialCommonFactorsChecked;
				num2 /= potentialCommonFactorsChecked;
				num3 /= potentialCommonFactorsChecked;
				
				potentialCommonFactorsChecked = 1;
			}
			
			else if(potentialCommonFactorsChecked >= maxLoop && timesNumSimplified == 0)
			{
				return false;
			}
			
			potentialCommonFactorsChecked += 1;
		}
		
		// Full simplified root
		System.out.println(String.format(" %.2f +/- %d√%d", num1, num2, insideRoot));
        
        // Denominator line
	    System.out.println(String.format("------------------"));
	    System.out.println(String.format("      %.2f", num3));
	    System.out.println("");
		
		return true;
	}
	
}

