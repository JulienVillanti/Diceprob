import java.util.Scanner;
import java.util.Random;

public class Test5 {
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
            System.out.printf(" Sum   Should Occur    %% Occurred    Should Occur (util.Random)   %% Occurred (util.Random)   Should Occur (Math.random)   %% Occurred (Math.random)%n");
            System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------%n");

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

                System.out.printf("%3d  (%2d) %4.2f%% %23d %20.2f%% %25d %22.2f%%%n", total, baseCount[total], probabilityBase, randomRollCounts[total], probabilityRandom, mathRandomRollCounts[total], probabilityMathRandom);
            }

            System.out.print("Would you like to roll the dice another time? (y/n): ");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));
        System.out.println("Thank you for using my program!");
    }
}

