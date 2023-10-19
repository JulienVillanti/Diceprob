import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain;

        System.out.println("Welcome to the dice simulator");

        System.out.print("What is the number of sides of each die: ");
        dieSides = kb.nextInt();

        DecimalFormat df = new DecimalFormat("0.00");

        // Define column widths
        int columnWidth = 15;

        // Print table headers

        do {
            System.out.print("How many times do you want to roll the dice?: ");
            numOfRolls = kb.nextInt();

            int numOutcomes = dieSides * dieSides;
            int[][] outcomesCount = new int[dieSides + 1][dieSides + 1];
            int[][] randomRollOutcome = new int[dieSides + 1][dieSides + 1];
            int[][] mathRandomOutcomes = new int[dieSides + 1][dieSides + 1];

            int[] baseRollCounts = new int[(dieSides + dieSides) + 1];
            int[] randomRollCounts2 = new int[(dieSides + dieSides) + 1];
            int[] mathRandomRollCounts = new int[(dieSides + dieSides) + 1];

            // ... (your existing code for calculating probabilities and roll counts)
            int[] baseCount = new int[(dieSides * 2) + 1];
            for (int die1 = 1; die1 <= dieSides; die1++) {
                for (int die2 = 1; die2 <= dieSides; die2++) {
                    int total = die1 + die2;
                    outcomesCount[die1][die2]++;
                    baseCount[total]++;
                }
            }
            System.out.printf("%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%-" + columnWidth + "s%n", "Sum", "Base Rolls", "Base Probability (%)", "Random Rolls", "Random Probability (%)", "Math.random Probability (%)");
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
            int[] mathCount = new int[(dieSides * 2) + 1];
            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = (int) (Math.random() * dieSides) + 1;
                int die2 = (int) (Math.random() * dieSides) + 1;
                int totalMathRandom = die1 + die2;
                mathRandomOutcomes[die1][die2]++;
            }


            for (int total = 2; total <= (dieSides + dieSides); total++) {
                double baseProbability = 0;
                double randomProbability = 0;
                double mathRandomProbability = 0;

                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = total - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        baseProbability += (outcomesCount[die1][die2] / (double) numOutcomes) * 100;
                        randomProbability += (randomRollOutcome[die1][die2] / (double) numOfRolls) * 100;
                        mathRandomProbability += (mathRandomOutcomes[die1][die2] / (double) numOfRolls) * 100;
                    }
                }

                // Print formatted row with specified column width
                System.out.printf("%-" + columnWidth + "d%-" + columnWidth + "d%-" + columnWidth + "s%-" + columnWidth + "d%-" + columnWidth + "s%-" + columnWidth + "s%n",
                        total,"()" +  baseCount[total], df.format(baseProbability) + "%", randomRollCounts[total], df.format(randomProbability) + "%", df.format(mathRandomProbability) + "%");
            }

            System.out.println("Would you like to roll the dice another time? (y/n)");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));
    }
}





