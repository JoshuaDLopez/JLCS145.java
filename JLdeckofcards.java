import java.util.*;

public class jldeckofcards {

    // Define constants
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a deck of cards
        List<String> deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(rank + " of " + suit);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Create stacks for the player and dealer
        Stack<String> playerHand = new Stack<>();
        Stack<String> dealerHand = new Stack<>();

        // Create a queue for the deck
        Queue<String> deckQueue = new LinkedList<>(deck);

        // Deal initial cards
        playerHand.push(deckQueue.poll());
        dealerHand.push(deckQueue.poll());
        playerHand.push(deckQueue.poll());
        dealerHand.push(deckQueue.poll());

        // Display initial hands
        System.out.println("Player's Hand: " + playerHand);
        System.out.println("Dealer's Hand: " + dealerHand.peek() + " [Hidden]");

        // Player's turn
        while (true) {
            System.out.print("Do you want to hit or stand? (h/s): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("h")) {
                playerHand.push(deckQueue.poll());
                System.out.println("Player's Hand: " + playerHand);

                int playerTotal = calculateHandValue(playerHand);
                if (playerTotal > 21) {
                    System.out.println("Player busts! Dealer wins.");
                    return;
                }
            } else if (choice.equals("s")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // Dealer's turn
        while (true) {
            int dealerTotal = calculateHandValue(dealerHand);

            if (dealerTotal < 17) {
                dealerHand.push(deckQueue.poll());
                System.out.println("Dealer hits: " + dealerHand.peek());
            } else {
                break;
            }
        }

        // Display final hands
        System.out.println("Player's Hand: " + playerHand);
        System.out.println("Dealer's Hand: " + dealerHand);

        // Calculate totals
        int playerTotal = calculateHandValue(playerHand);
        int dealerTotal = calculateHandValue(dealerHand);

        // Determine the winner
        if (playerTotal > dealerTotal) {
            System.out.println("Player wins!");
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Function to calculate the value of a hand
    private static int calculateHandValue(Stack<String> hand) {
        int value = 0;
        int numAces = 0;

        // Iterate over the cards in the hand
        for (String card : hand) {
            String rank = card.split(" ")[0];

            // Check for face cards and Aces
            if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                value += 10;
            } else if (rank.equals("Ace")) {
                numAces++;
                value += 11;
            } else {
                value += Integer.parseInt(rank);
            }
        }

        // Adjust the value if there are Aces and the total exceeds 21
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }
}
