import java.util.Scanner;
import java.util.Random;

public class Test3 {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain;

        System.out.println("Welcome to the dice probability calculator!");

        do {
            System.out.print("Please enter the number of sides of your dice: ");
            dieSides = kb.nextInt();

            int totalOutcomes = dieSides * dieSides;
            int[] allOutcomes = new int[2 * dieSides + 1];
            int[] mathRandomOutcomes = new int[2 * dieSides + 1];
            int[] javaRandomOutcomes = new int[2 * dieSides + 1];

            System.out.print("Please enter the number of rolls: ");
            numOfRolls = kb.nextInt();

            // Simulate dice rolls and count outcomes for all, Math.random, and Java Random rolls
            Random random = new Random();
            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = (int) (Math.random() * dieSides) + 1;
                int die2 = (int) (Math.random() * dieSides) + 1;
                int javaRandomDie1 = random.nextInt(dieSides) + 1;
                int javaRandomDie2 = random.nextInt(dieSides) + 1;
                int sumMathRandom = die1 + die2;
                int sumJavaRandom = javaRandomDie1 + javaRandomDie2;

                allOutcomes[sumMathRandom]++;
                mathRandomOutcomes[sumMathRandom]++;
                allOutcomes[sumJavaRandom]++;
                javaRandomOutcomes[sumJavaRandom]++;
            }

            // Display the table header
            System.out.println("Sum  Should Occur  % Occurred (Math.Random)  % Occurred (Java Random)");
            System.out.println("------------------------------------------------------------");

            // Calculate and display probabilities for all rolls
            for (int sum = 2; sum <= 2 * dieSides; sum++) {
                double totalOccurrences = allOutcomes[sum];
                double probabilityMathRandom = (mathRandomOutcomes[sum] / totalOccurrences) * 100;
                double probabilityJavaRandom = (javaRandomOutcomes[sum] / totalOccurrences) * 100;
                //System.out.printf("%-3d  %.2f%%         %.2f%%                 %.2f%%\n", sum, 100.0 / totalOutcomes, probabilityMathRandom, probabilityJavaRandom);
                System.out.printf("%-3d  %.2f%%%14s%.2f%%%21s%.2f%%\n", sum, 100.0 / totalOutcomes,
                        "(" + numOfRolls + ")", probabilityMathRandom,
                        "(" + numOfRolls + ")", probabilityJavaRandom);
            }

            System.out.print("Would you like to roll again? (y/n): ");
            rollAgain = kb.next();
        } while (rollAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for using the Dice Probability Calculator! Until next time!");
    }
}
