import java.util.Scanner;
import java.util.Random;

public class test4 {


    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int dieSides;
        int numOfRolls;
        String rollAgain;


        System.out.println("Welcome to dice probability calculator !!");

        do {
            System.out.print("Please enter the number of sides of your dice: ");
            dieSides = kb.nextInt();

            int totalOutcomes = dieSides * dieSides;
            int[][] rollOutcomes = new int[dieSides + 1][dieSides + 1];
            int[][] rollOutcomesRandom = new int[dieSides + 1][dieSides + 1];
            int[][] OutcomesMathRandom = new int[dieSides + 1][dieSides + 1];

            System.out.print("Please enter the number of rolls: ");
            numOfRolls = kb.nextInt();

            //Simulation of the base roll number
            for (int die1 = 1; die1 <= dieSides; die1++) {
                for (int die2 = 1; die2 <= dieSides; die2++) {
                    int sum = die1 + die2;
                    rollOutcomes[die1][die2]++;
                }
            }
            // Calculation of the probability
            for (int sum = 2; sum <= dieSides * 2; sum++) {
                double totalOccurrences = 0;
                for (int die1 = 1; die1 <= dieSides; die1++) {
                    int die2 = sum - die1;
                    if (die2 >= 1 && die2 <= dieSides) {
                        totalOccurrences += rollOutcomes[die1][die2];
                    }
                }
                double probability = (totalOccurrences / totalOutcomes) * 100;
                System.out.println("Total " + sum + ": Probability = " + probability + "%");


            }
            System.out.println("Would you like to roll again ? (y/n)");
            rollAgain = kb.next();

        } while (rollAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for using my program ! Until next time!");
    }
}

