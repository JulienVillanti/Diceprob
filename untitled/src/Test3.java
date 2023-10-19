import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test3 {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain;

        System.out.println("Welcome to the Dice Probability Calculator!");

        do {
            System.out.print("Please enter the number of sides of your dice: ");
            dieSides = kb.nextInt();

            int[] allOutcomes = new int[2 * dieSides + 1];
            int[] randomOutcomes = new int[2 * dieSides + 1];
            List<Integer> spreadOutRolls = new ArrayList<>();

            System.out.print("Please enter the number of rolls: ");
            numOfRolls = kb.nextInt();

            // Simulate dice rolls and count outcomes for all and random rolls
            Random random = new Random();
            for (int roll = 0; roll < numOfRolls; roll++) {
                int die1 = (int) (Math.random() * dieSides) + 1;
                int die2 = (int) (Math.random() * dieSides) + 1;
                int javaRandomDie1 = random.nextInt(dieSides) + 1;
                int javaRandomDie2 = random.nextInt(dieSides) + 1;
                int sumMathRandom = die1 + die2;
                int sumJavaRandom = javaRandomDie1 + javaRandomDie2;

                allOutcomes[sumMathRandom]++;
                randomOutcomes[sumJavaRandom]++;
                spreadOutRolls.add(sumMathRandom);
                spreadOutRolls.add(sumJavaRandom);
            }

            // Display outcomes for all rolls in a table
            System.out.println("Outcomes for all rolls:");
            System.out.println("Roll\tSum\tProbability");
            for (int sum = 2; sum <= 2 * dieSides; sum++) {
                double totalOccurrences = allOutcomes[sum];
                double probability = (totalOccurrences / numOfRolls) * 100;
                System.out.printf("%d\t%d\t%.2f%%\n", sum - 1, sum, probability);
            }

            // Display outcomes for random rolls in a table
            System.out.println("Outcomes for random rolls:");
            System.out.println("Roll\tSum\tProbability");
            for (int sum = 2; sum <= 2 * dieSides; sum++) {
                double totalOccurrences = randomOutcomes[sum];
                double probability = (totalOccurrences / numOfRolls) * 100;
                System.out.printf("%d\t%d\t%.2f%%\n", sum - 1, sum, probability);
            }

            // Display spread out rolls in a table
            System.out.println("Spread out rolls:");
            System.out.println("Roll\tSum");
            for (int i = 0; i < spreadOutRolls.size(); i += 2) {
                System.out.printf("%d\t%d\n", i / 2 + 1, spreadOutRolls.get(i));
            }

            System.out.print("Would you like to roll again? (y/n): ");
            rollAgain = kb.next();
        } while (rollAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for using the Dice Probability Calculator! Until next time!");
    }
}