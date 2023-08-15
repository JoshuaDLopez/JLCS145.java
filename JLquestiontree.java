import java.io.PrintStream;
import java.util.Scanner;

public class QuestionTree {
    private QuestionNode overallRoot;
    private UserInterface ui;
    private int gamesPlayed;
    private int gamesWon;

    public QuestionTree(UserInterface ui) {
        this.ui = ui;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.overallRoot = new QuestionNode("computer");
    }

    public void play() {
        overallRoot = play(overallRoot);
        gamesPlayed++;
    }

    private QuestionNode play(QuestionNode current) {
        if (current.yes == null && current.no == null) {
            ui.print("Would your object happen to be " + current.data + "? ");
            if (ui.nextBoolean()) {
                ui.println("I win!");
                gamesWon++;
            } else {
                ui.print("I lose. What is your object? ");
                String newAnswer = ui.nextLine();
                ui.print("Type a yes/no question to distinguish your item from " + current.data + ": ");
                String newQuestion = ui.nextLine();
                ui.print("And what is the answer for your object? ");
                boolean newAnswerIsYes = ui.nextBoolean();

                QuestionNode newQuestionNode = new QuestionNode(newQuestion);
                QuestionNode newAnswerNode = new QuestionNode(newAnswer);

                if (newAnswerIsYes) {
                    newQuestionNode.yes = newAnswerNode;
                    newQuestionNode.no = current;
                } else {
                    newQuestionNode.yes = current;
                    newQuestionNode.no = newAnswerNode;
                }

                return newQuestionNode;
            }
        } else {
            ui.print(current.data + " ");
            if (ui.nextBoolean()) {
                current.yes = play(current.yes);
            } else {
                current.no = play(current.no);
            }
            return current;
        }
    }

    public void save(PrintStream output) {
        save(output, overallRoot);
    }

    private void save(PrintStream output, QuestionNode current) {
        if (current.yes == null && current.no == null) {
            output.println("A:" + current.data);
        } else {
            output.println("Q:" + current.data);
            save(output, current.yes);
            save(output, current.no);
        }
    }

    public void load(Scanner input) {
        overallRoot = load(input);
    }

    private QuestionNode load(Scanner input) {
        String line = input.nextLine();
        String[] parts = line.split(":", 2);
        QuestionNode current = new QuestionNode(parts[1].trim());

        if (parts[0].equals("Q")) {
            current.yes = load(input);
            current.no = load(input);
        }

        return current;
    }

    public int totalGames() {
        return gamesPlayed;
    }

    public int gamesWon() {
        return gamesWon;
    }
}
