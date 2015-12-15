import java.io.*;
import java.util.*;
public class GolfDriver {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name of a scores file or Q to quit :");
            String input = reader.nextLine();
            if (input.equals("Q") || input.equals("q"))
                break;
            GolfScoreCard card = new GolfScoreCard(input);

            System.out.println("Here is a complete history of scores: \n" +
                    card.toString() + "\n" +
                    card.highLowDays() + "\n" +
                    card.bestWorstHoles());
            System.out.println();
        }
    }
}