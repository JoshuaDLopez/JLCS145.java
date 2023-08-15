//Joshua Lopez 
//may 15th 2023
// cs 141
// lab 4 guessing game


   import java.util.Scanner;

public class jlguessinggame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//introduces scanner
        int totalGames = 0;
        int totalGuesses = 0;//sets integers
        int bestGame = 0;
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and 100");
        System.out.println("and will allow you to guess until you get it.");
        System.out.println("For each guess, I will tell you whether the right answer");//intro out of method
        System.out.println("is higher or lower than your guess.");
        System.out.println();

        String playAgain;//first method. starts game and produces random number
        do {
            int guesses = 0;
            int answer = getRandomNumber(100);
            System.out.println("I'm thinking of a number between 1 and 100...");
            
            int guess;
            do {
                System.out.print("Your guess? ");
                guess = scanner.nextInt();
                guesses++;
                
                if (guess < answer) {
                    System.out.println("It's higher.");
                } else if (guess > answer) {
                    System.out.println("It's lower.");
                } else {
                    System.out.println("You got it right in " + guesses + " guess" + (guesses == 1 ? "" : "es") + "!");//prints the intro and whether a guess is higher or lower 
                }
            } while (guess != answer);
            
            totalGames++;
            totalGuesses += guesses;
            if (bestGame == 0 || guesses < bestGame) {//tallies up total games
                bestGame = guesses;
            }
            
            System.out.print("Do you want to play again? ");
            playAgain = scanner.next();
            System.out.println();
        } while (playAgain.toLowerCase().startsWith("y"));//lets the user type y or n to play another game
        
        double averageGuesses = (double) totalGuesses / totalGames;
        
        System.out.println("Overall results:");//produces the end of the game by showing stats of all games
        System.out.println("    Total games = " + totalGames);
        System.out.println("    Total guesses = " + totalGuesses);
        System.out.printf("    Guesses/game = %.1f%n", averageGuesses);
        System.out.println("    Best game = " + bestGame);
        
        scanner.close();
    }
    
    public static int getRandomNumber(int range) {
        return (int) (1 + Math.random() * range);//sets up random number
    }
}
   
          
      
      
      
