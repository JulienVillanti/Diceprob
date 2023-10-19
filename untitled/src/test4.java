import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class test4 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain = null;

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

            DecimalFormat df = new DecimalFormat("0.00");

            // Print table headers
            //System.out.printf("%-5d (%6d) %11s%% (%6d) %13s%% (%6d) %n", total, baseCount[total], formattedProbBase, randomRollCounts[total], formattedProbRandom, formattedProbMathRandom);
            System.out.printf("%-5s %-18s %-18s %-18s%n", "Sum", "Should Occur", "% Occurred (util.Random)", "% Occurred (Math.random)");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");


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

                double probabilityBase = (totalOccurrencesBase / numOutcomes) * 100;
                double probabilityRandom = (totalOccurrencesRandom / numOfRolls) * 100;
                double probabilityMathRandom = (totalOccurrencesMathRandom / numOfRolls) * 100;

                String formattedProbBase = df.format(probabilityBase);
                String formattedProbRandom = df.format(probabilityRandom);
                String formattedProbMathRandom = df.format(probabilityMathRandom);

                System.out.printf("%-5d (%-6d) %11s%% (%-6d) %6s %6s %13s%%%n", total, baseCount[total], probabilityBase, randomRollCounts[total], probabilityRandom, mathRandomRollCounts[total], probabilityMathRandom);


                System.out.println("Would you like to roll the dice another time? (y/n)");
                rollAgain = kb.next();
            }

        } while (Objects.requireNonNull(rollAgain).equalsIgnoreCase("y"));
            System.out.println("Thank you for using my program!");
            kb.close();
        }
    }
        
        



