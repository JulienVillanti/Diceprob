/*----------------------------
ASSIGNMENT 2
Written by JULIEN VILLANTI - 2390054
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Hello and welcome to the dice probability calculator!");

        while (true) {
            System.out.println("Please enter the number of sides of each die: ");
            int dieFace = kb.nextInt();

            int[][] dieResultsCounter = new int[dieFace][dieFace];

            System.out.println("How many times do you want to roll the dice? ");
            int diceRolls = kb.nextInt();

            //simulateDiceRolls(dieFace, dieResultsCounter, diceRolls);
            simulateDiceRollsRandom(random, dieFace, dieResultsCounter, diceRolls);


            System.out.println("SUM\tExpected\tActual\tPercentage");
            for (int sum = 2; sum <= 2 * dieFace; sum++) {
                int expectedFrequency = calculExpectedFrequency(dieFace, sum);
                int actualFrequency = realFrequency(dieResultsCounter, dieFace, sum);
                double ratio = (double) actualFrequency / diceRolls * 100;
                System.out.println(sum + "\t" + expectedFrequency + "\t" + actualFrequency + "\t" + ratio + "%");

            }
            System.out.println("Would you like to roll the dice again ? y/n: ");
            String rollAgain = kb.next().toLowerCase();
            if (!rollAgain.equals("y")) {
                System.out.println("Thank you for using my program ! Goodbye!");
                break;
            }
        }
    }


    private static void simulateDiceRollsRandom(Random random, int dieFace, int[][] dieResultsCounter, int diceRolls) {
        for (int i = 0; i < diceRolls; i++) {
            int die1 = random.nextInt(dieFace) + 1;
            int die2 = random.nextInt(dieFace) + 1;
            dieResultsCounter[die1 - 1][die2 - 1]++;
        }
    }
    /*private static void simulateDiceRolls(int dieFace, int[][] dieResultsCounter, int diceRolls) {
        for (int i = 0; i < diceRolls; i++) {
            int die1 = dieFace + 1;
            int die2 = dieFace + 1;
            dieResultsCounter[die1 - 1][die2 - 1]++;
        }
    }*/

    private static int calculExpectedFrequency(int dieFace, int sum) {
        int validComb = 0;
        for (int die1 = 1; die1 <= dieFace; die1++) {
            for (int die2 = 1; die2 <= dieFace; die2++) {
                if (die1 + die2 == sum) {
                    validComb++;
                }
            }
        }
        return validComb;
    }

    private static int realFrequency(int[][] dieResultsCounter, int dieFace, int sum) {
        int frequency = 0;
        for (int die1 = 1; die1 <= dieFace; die1++) {
            for (int die2 = 1; die2 < dieFace; die2++) {
                if (die1 + die2 == sum) {
                    frequency += dieResultsCounter[die1 - 1][die2 - 1];
                }
            }
        }
        return frequency;
    }

}


