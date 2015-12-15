import java.util.*;
import java.io.*;

public class GolfScoreCard {
    private int[] dates;
    private int[][] scores;

    public GolfScoreCard(String fileName) throws IOException {
        Scanner fileReader = new Scanner(new File(fileName));

        int numDays = fileReader.nextInt();

        dates = new int[numDays];
        scores = new int[numDays][18];

        for (int i = 0; i < numDays; i++) {
            dates[i] = fileReader.nextInt();
            for (int j = 0; j < 18; j++) {
                scores[i][j] = fileReader.nextInt();
            }
        }
        fileReader.close();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < dates.length; i++) {
            str += "Date: " + dates[i] + " Scores:";
            for (int j = 0; j < 18; j++) {
                str += " " + scores[i][j];
            }
            str += "\n";
        }
        return str;
    }

    public String highLowDays() {
        int indexLow = 0;
        int indexHigh = 0;
        int lowTotal = dayTotal(0);
        int highTotal = dayTotal(0);

        for (int i = 1; i < dates.length; i++) {
            int todayTotal = dayTotal(i);
            if (todayTotal < lowTotal) {
                indexLow = i;
                lowTotal = todayTotal;
            } else if (todayTotal > highTotal) {
                indexHigh = i;
                highTotal = todayTotal;
            }
        }

        String str = "";
        str += "The best day: " + dates[indexLow] +
                " score: " + lowTotal + "\n";
        str += "The worst day: " + dates[indexHigh] +
                " score: " + highTotal + "\n";
        return str;
    }

    private int dayTotal(int i) {
        int total = 0;
        for (int j = 0; j < 18; j++)
            total += scores[i][j];
        return total;
    }

    public String bestWorstHoles() {
        String str;
        int indexLowCol = 0;
        int indexHighCol = 0;

        double worstAverage = 0;
        double bestAverage = 1000;

        double sum = 0;
        double average = 0;

        for (int col = 0; col < scores[0].length; col++) {    //index of hole being checked
            for (int row = 0; row < scores.length; row++) {   //runs through the 4 rows of the column
                sum += scores[row][col];                      //sums the scores of the column
            }
            average = sum / scores.length;                    //finds average score of hole

            if (average > worstAverage) {
                worstAverage = average;
                indexLowCol = col;
            }
            if (average < bestAverage) {
                bestAverage = average;
                indexHighCol = col;
            }
            average = 0;
            sum = 0;
        }

        str = "The best hole: " + (indexHighCol + 1) + " average: " + bestAverage + "\n" +
                "The worst hole: " + (indexLowCol + 1) + " average: " + worstAverage;
        return str;
    }
}
