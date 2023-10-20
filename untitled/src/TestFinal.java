// -----------------------------------------------------
// Assignment 2 - Random Number Generator
// Written by: Julien Villanti - 2390054
// This assignment asked us ton simulate the rolling of two dice, using two different objects, util.Random and Math.random, to generate the rolls.A 2-D array was needed to store the sum of each randomly generated roll.
//The steps are the following:
/*      1. Display a welcoming message
        2. Ask the user how many sides each die has
        3. Create the 2-d array to hold the frequency counter of each possible sums
        4. Ask the user how many times they want the dice to be rolled using both methods
        5. Print the expected frequencies and actual frequencies and percentages each sum appeared in both methods in a tabular format.
        6. Repeat steps 3 to 5 as long as the user wants.
        7. End with a closing message
*/
// -----------------------------------------------------


import java.util.Scanner;
import java.util.Random;

public class TestFinal {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain;

        System.out.println("Welcome to the dice simulator");

        System.out.print("What is the number of sides of each die: ");
        dieSides = kb.nextInt();

        //Creating the loop to ask the user how many rolls they want, repeating step 3 to 5
        do {
            System.out.print("How many times do you want to roll the dice?: ");
            numOfRolls = kb.nextInt();

            //Calculate the number of possible outcomes and initiate the arrays for the different calculation methods
            int numOutcomes = dieSides * dieSides;
            int[][] outcomesCount = new int[dieSides + 1][dieSides + 1];
            int[][] randomRollOutcome = new int[dieSides + 1][dieSides + 1];
            int[][] mathRandomOutcomes = new int[dieSides + 1][dieSides + 1];

            //Calculate the base probability by iterating through all the possible results of two dice rolls
            int[] baseCount = new int[(dieSides * 2) + 1];
            for (int die1 = 1; die1 <= dieSides; die1++) {
                for (int die2 = 1; die2 <= dieSides; die2++) {
                    int total = die1 + die2;
                    outcomesCount[die1][die2]++;
                    baseCount[total]++;
                }
            }

            //Simulating dice rolls with util.Random and register the outcomes
            Random rolls = new Random();
            int[] randomRollCounts = new int[(dieSides * 2) + 1];

            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = rolls.nextInt(dieSides) + 1;
                int die2 = rolls.nextInt(dieSides) + 1;
                int totalRandom = die1 + die2;
                randomRollOutcome[die1][die2]++;
                randomRollCounts[totalRandom]++;
            }

            // Simulating dice rolls with Math.random and register the outcomes
            int[] mathRandomRollCounts = new int[(dieSides * 2) + 1];
            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = (int) (Math.random() * dieSides) + 1;
                int die2 = (int) (Math.random() * dieSides) + 1;
                int totalMathRandom = die1 + die2;
                mathRandomOutcomes[die1][die2]++;
                mathRandomRollCounts[totalMathRandom]++;
            }

            // Print table headers
            System.out.printf("%-10s\t%-20s \t%-20s \t%-20s%n", "Sum", "Should Occur", "% Occurred ", "% Occurred");
            System.out.printf("%-10s %-19s \t %-10s \t%19s%n", "   ", " ", " (util.Random) ", "  (Math.random)");
            System.out.println("--------------------------------------------------------------------------");


            //Calculating and storing the number of time each sum occurs
            for (int total = 2; total <= (dieSides + dieSides); total++) {
                //Total occurrences for each method
                double totalOccurrencesBase = 0;
                double totalOccurrencesRandom = 0;
                double totalOccurrencesMathRandom = 0;

                //Iterating through the possible combinations of the two dice rolls
                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrencesBase += outcomesCount[die1][die2];
                        totalOccurrencesRandom += randomRollOutcome[die1][die2];
                        totalOccurrencesMathRandom += mathRandomOutcomes[die1][die2];
                    }
                }
                //Calculate the probabilities
                double probabilityBase = (totalOccurrencesBase / numOutcomes) * 100;
                double probabilityRandom = (totalOccurrencesRandom / numOfRolls) * 100;
                double probabilityMathRandom = (totalOccurrencesMathRandom / numOfRolls) * 100;

                //Formatting and displaying the output using printf
                System.out.printf("%3d  \t   (%2d)\t%6.2f%%  \t   (%6d)\t%6.2f%%  \t   (%6d)\t%6.2f%%\n", total, baseCount[total], probabilityBase, randomRollCounts[total], probabilityRandom, mathRandomRollCounts[total], probabilityMathRandom);
            }

            System.out.print("Would you like to roll the dice another time? (any character but y to quit): ");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for using my program! Goodbye");
    }
}