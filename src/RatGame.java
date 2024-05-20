import java.util.Scanner;


public class RatGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RatGameManager gameManager = RatGameManager.getInstance();

    public static void main(String[] args) {
        System.out.println("Welcome to the Rat Game!");
        System.out.println("Your goal is to navigate through the rooms and find the cheese in the kitchen.");

        boolean playAgain;
        do {
            gameManager.initializeGame();
            chooseMovementStrategy();
            gameManager.playGame();
            playAgain = promptPlayAgain();
        } while (playAgain);

        System.out.println("Thank you for playing! Your total cheese(s): " + gameManager.getTotalScore());
    }

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static boolean promptPlayAgain() {
        System.out.println("Do you want to play again? (y/n): ");
        String response = getUserInput().trim().toLowerCase();
        return response.equals("y");
    }

    public static void chooseMovementStrategy() {
        System.out.println("Choose the rat's movement strategy (run/walk): ");
        String strategy = getUserInput().trim().toLowerCase();
        if (strategy.equals("run")) {
            gameManager.getRat().setMovementStrategy(new RunMovementStrategy());
        } else {
            gameManager.getRat().setMovementStrategy(new WalkMovementStrategy());
        }
    }
}