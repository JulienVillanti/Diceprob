import java.util.Scanner;
import java.util.Random;


public class Test3 {
  private static int numberOfSides(Scanner kb){
      System.out.println("Please enter the number of sides of your dice: ");
      return kb.nextInt();
    }

    private static int numberOfRolls(Scanner kb){
        System.out.println("Please enter the number of rolls: ");
        return kb.nextInt();
    }

    private static void rollSimulation(int[][] frequency, int numSides, int numberOfRolls){
      for ()

    }

    private static void calculateNonRandomProbabilities(int[][] frequency, int numSides) {
        for (int die1 = 1; die1 <= numSides; die1++) {
            for (int die2 = 1; die2 <= numSides; die2++) {
                int sum = die1 + die2;
                frequency[sum - 2][0]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("How good is the Dice probability calculator?");


        int numSides = numberOfSides(kb);
        int sumRange = 2 * numSides;
        int[][] frequency = new int[sumRange][2];

    }


}
