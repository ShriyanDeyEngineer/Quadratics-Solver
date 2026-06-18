package AcademicMentorLegacyProject;

import java.util.Scanner;

public class RunProgram 
{

	public static void main(String[] args) 
	{	
		Scanner reader = new Scanner(System.in);
		int continueProgram = 1;
		
		
		// Giving the user instructions on how to use the program
		System.out.println("You want to factor and/or solve a quadratic. First enter the a term (xSqaredCoefficient), then b term (xCoefficient), and then the c term (onesTerm).");
		System.out.println("MAKE SURE TO ACCOUNT FOR POSITIVES/NEGATIVES IN YOUR INPUTS. For example, if you want to factor x^2 - 7x + 5, you enter 1 for the a term, -7 for the b term, and 5 for the c term.");
		System.out.println("If there is no b or c term in the quadratic, enter '0' for that term. Please do not enter '0' for your a term because then you are not even working with a quadratic.");
		System.out.println("");
		
		
		while(continueProgram == 1)
		{
			// Obtain the quadratic expression from the user
			double xSquaredCoefficient;
			double xCoefficient;
			double onesTerm;
			
			
			// Variables to store the user's inputs
			System.out.println("Enter the a term of the quadratic expression:");
			xSquaredCoefficient = reader.nextDouble();
			System.out.println("");
			
			System.out.println("Enter the b term of the quadratic expression:");
			xCoefficient = reader.nextDouble();
			System.out.println("");
			
			System.out.println("Enter the c term of the quadratic expression:");
			onesTerm = reader.nextDouble();
			System.out.println("");
			
			
			// Checking to see if the user can follow directions
			while(xSquaredCoefficient == 0)
			{
				System.out.println("This is not a quadratic experssion because you have entered '0' for the a term. Try again.");
				xSquaredCoefficient = reader.nextDouble();
				System.out.println("");
			}
					
			// Asking user for the desired method to find the solutions to their quadratic expression
			int methodChoice;
			System.out.println("Enter '1' if you want to solve/factor the expression using the diamond problem method. Enter '2' to solve by using completing the square. Enter '3' to solve by using the quadratic forumula.");
			System.out.println("");
			methodChoice = reader.nextInt();
			System.out.println("");
			
			while(methodChoice != 1 && methodChoice != 2 && methodChoice != 3)
			{
				System.out.println("That is not a valid input. Enter '1', '2', or '3':");
				methodChoice = reader.nextInt();
				System.out.println("");
			}
					
			
			// Beginning and completing the factoring a solving process for the given quadratic expression
			System.out.println("Now factoring/solving: " + xSquaredCoefficient + "x^2 + " + xCoefficient + "x + " + onesTerm + "...");		
			SolvingMethods quadraticExpression = new SolvingMethods(xSquaredCoefficient, xCoefficient, onesTerm);
			System.out.println("");
			
			
			// Solving the given quadratic expression depending on the user's method of choice
			if(methodChoice == 1)
				quadraticExpression.diamondProblemMethod();
			else if(methodChoice == 2)
				quadraticExpression.completingTheSquare(xSquaredCoefficient, xCoefficient, onesTerm);
			else if(methodChoice == 3)
				quadraticExpression.quadraticFormula(xSquaredCoefficient, xCoefficient, onesTerm);
			
			
			// Checking to see if the user would like to input another quadratic expression to factor/solve
			System.out.println("");
			System.out.println("");
			System.out.println("Would you like to factor/solve another quadratic expression? Enter '1' for 'yes' or '0' for 'no':");
			continueProgram = reader.nextInt();
			System.out.println("");
			while(continueProgram != 1 && continueProgram != 0)
			{
				System.out.println("That is not a valid input. Enter '1' or '0'.");
				continueProgram = reader.nextInt();
				System.out.println("");
			}
		}
		
		System.out.print("Goodbye, student. Thanks for using this program!");
	}

}

