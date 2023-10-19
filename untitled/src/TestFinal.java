// -----------------------------------------------------
// Assignment 2 - Random Number Generator
// Written by: Julien Villanti - 2390054
// Short Description of your project/code and how you designed it.
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

        do {
            System.out.print("How many times do you want to roll the dice?: ");
            numOfRolls = kb.nextInt();

            int numOutcomes = dieSides * dieSides;
            int[][] outcomesCount = new int[dieSides + 1][dieSides + 1];
            int[][] randomRollOutcome = new int[dieSides + 1][dieSides + 1];
            int[][] mathRandomOutcomes = new int[dieSides + 1][dieSides + 1];

            // Base probability
            int[] baseCount = new int[(dieSides * 2) + 1];
            for (int die1 = 1; die1 <= dieSides; die1++) {
                for (int die2 = 1; die2 <= dieSides; die2++) {
                    int total = die1 + die2;
                    outcomesCount[die1][die2]++;
                    baseCount[total]++;
                }
            }

            // Random probability
            Random rolls = new Random();
            int[] randomRollCounts = new int[(dieSides * 2) + 1];

            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = rolls.nextInt(dieSides) + 1;
                int die2 = rolls.nextInt(dieSides) + 1;
                int totalRandom = die1 + die2;
                randomRollOutcome[die1][die2]++;
                randomRollCounts[totalRandom]++;
            }

            // Math.random probability
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

            for (int total = 2; total <= (dieSides + dieSides); total++) {
                double totalOccurrencesBase = 0;
                double totalOccurrencesRandom = 0;
                double totalOccurrencesMathRandom = 0;

                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrencesBase += outcomesCount[die1][die2];
                        totalOccurrencesRandom += randomRollOutcome[die1][die2];
                        totalOccurrencesMathRandom += mathRandomOutcomes[die1][die2];
                    }
                }
                //Determining the percentage of probability
                double probabilityBase = (totalOccurrencesBase / numOutcomes) * 100;
                double probabilityRandom = (totalOccurrencesRandom / numOfRolls) * 100;
                double probabilityMathRandom = (totalOccurrencesMathRandom / numOfRolls) * 100;

                //Formatting the output using printf
                System.out.printf("%3d  \t   (%2d)\t%6.2f%%  \t   (%6d)\t%6.2f%%  \t   (%6d)\t%6.2f%%\n", total, baseCount[total], probabilityBase, randomRollCounts[total], probabilityRandom, mathRandomRollCounts[total], probabilityMathRandom);
            }


            System.out.print("Would you like to roll the dice another time? (any character but y to quit): ");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));
        System.out.println("Thank you for using my program!");
    }
}