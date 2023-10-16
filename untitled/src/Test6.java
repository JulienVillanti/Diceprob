import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Test6 {
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
            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = (int) (Math.random() * dieSides) + 1;
                int die2 = (int) (Math.random() * dieSides) + 1;
                int totalMathRandom = die1 + die2;
                mathRandomOutcomes[die1][die2]++;
            }

            DecimalFormat df = new DecimalFormat("0.00");

            System.out.println("Base Probabilities:");
            for (int total = 2; total <= (dieSides + dieSides); total++) {
                double totalOccurrences = 0;
                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrences += outcomesCount[die1][die2];
                    }
                }
                double probability = (totalOccurrences / numOutcomes) * 100;
                String formattedProb = df.format(probability);
                System.out.println("Sum " + total + ": Probability = " + formattedProb + "% with " + baseCount[total] + " rolls.");
            }

            System.out.println("User-Specific Probabilities (based on " + numOfRolls + " rolls):");
            for (int total = 2; total <= (dieSides + dieSides); total++) {
                double totalOccurrences = 0;
                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrences += randomRollOutcome[die1][die2];
                    }
                }
                double probability = (totalOccurrences / numOfRolls) * 100;
                String formattedProb = df.format(probability);
                System.out.println("Sum " + total + ": Probability (Random) = " + formattedProb + "% with " + randomRollCounts[total] + " rolls.");
            }

            System.out.println("User-Specific Probabilities (based on " + numOfRolls + " rolls):");
            for (int total = 2; total <= (dieSides + dieSides); total++) {
                double totalOccurrences = 0;
                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrences += mathRandomOutcomes[die1][die2];
                    }
                }
                double probability = (totalOccurrences / numOfRolls) * 100;
                String formattedProb = df.format(probability);
                System.out.println("Sum " + total + ": Probability (Math.random) = " + formattedProb + "%.");
            }

            System.out.println("Would you like to roll the dice another time? (y/n)");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));
    }
}

