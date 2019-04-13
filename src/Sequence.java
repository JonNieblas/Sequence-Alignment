/*
 * Dynamically aligns strings x and y using spaces, with the goal
 * of getting the strings to have similar compositions index-wise.
 *
 * Adds spaces on a point based system.
 * The system is as follows:
 *      For char i of string x and char j of string y:
 *          If i matches j, reward = 0 points.
 *          If i doesn't match j, reward = 1 point.
 *          If i is paired with a space, reward = 2 points.
 *          If j is paired with a space, reward = 2 points.
 * The system finds the optimal way to input spaces into both
 * strings by computing the score with the lowest amount of points,
 * then tracing backwards to build the two strings.
 */
public class Sequence {
    private static final int space = 2;

    // holds our best XY vals for testing
    private static String[] bestXY = new String[2];

    public Sequence(){
    }

    // Takes the table and builds our best strings.
    private static void buildAnswer(String x, String y, int m, int n, Score[][] alignedTable){
        int i = m;
        int j = n;
        int xIndex = m - 1;
        int yIndex = n - 1;
        String xBest = "";
        String yBest = "";

        while(i != 0 || j != 0){
            char choice = alignedTable[i][j].getChoice();
            if(choice == 'n'){
                xBest = x.charAt(xIndex) + xBest;
                yBest = y.charAt(yIndex) + yBest;
                xIndex--;
                yIndex--;
                i--;
                j--;
            }
            else if(choice == 'x'){
                yBest = "_" + yBest;
                xBest = x.charAt(xIndex) + xBest;
                xIndex--;
                i--;
            }
            else{
                xBest = "_" + xBest;
                yBest = y.charAt(yIndex) + yBest;
                yIndex--;
                j--;
            }
        }
        bestXY[0] = xBest;
        bestXY[1] = yBest;
    }

    // Inserts base case values into the table
    private static Score[][] fillBaseCases(int m, int n){
        Score[][] alignedTable = new Score[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            alignedTable[i][0] = new Score(space * i, 'n');
        }
        for(int j = 0; j <= n; j++){
            alignedTable[0][j] = new Score(space * j, 'n');
        }

        return alignedTable;
    }

    // return the correct point value based on the two characters given
    private static int getPairValue(char c, char d){
        if(c == d){
            return 0;
        }
            return 1; //default, c != d
    }

    // Builds the table of best scores
    public void startSequence(String x, String y){
        int m = x.length();
        int n = y.length();
        Score[][] alignedTable = fillBaseCases(m, n);


        for(int j = 1; j <= n; j++){
            char yChar = y.charAt(j - 1);
            for(int i = 1; i <= m; i++){
                char xChar = x.charAt(i - 1);
                int none = alignedTable[i - 1][j - 1].getPoints() + getPairValue(xChar, yChar);
                int xDash = alignedTable[i - 1][j].getPoints() + space;
                int yDash = alignedTable[i][j - 1].getPoints() + space;

                if(none <= xDash && none <= yDash){
                    alignedTable[i][j] = new Score(none, 'n');
                }
                else if(xDash <= yDash){
                    alignedTable[i][j] = new Score(xDash, 'x');
                }
                else{
                    alignedTable[i][j] = new Score(yDash, 'y');
                }
            }
        }

        buildAnswer(x, y, m, n, alignedTable);
        printTable(x, y, m, n, alignedTable);
        printBestStrings();
    }

    // Prints table containing all Scores
    private static void printTable(String x, String y, int m, int n, Score[][] alignedTable){
        int yIndex = 0;

        // Print table info:
        System.out.println("\nTable of each score's total points and optimal choice.");
        System.out.println(String.format("%30s", "x = place dash in string x"));
        System.out.println(String.format("%30s", "y = place dash in string y"));
        System.out.println(String.format("%44s", "n = don't place a dash in either string\n"));

        // Print top x-axis
        System.out.print(String.format("%4c %3c   ", '|', '-'));
        for(int k = 0; k < m; k++){
            System.out.print(String.format("|%4c   ", x.charAt(k)));
        }
        printRowLines(m);

        // Print individual cell values. Left to right, top to bottom
        for(int j = 0; j <= n; j++){
            if(j == 0){ // Print initial dash
                System.out.print(String.format("\n%-2c ", '-'));
            } else { // Print y-axis value
                System.out.print(String.format("\n%-2c ", y.charAt(yIndex)));
                yIndex++;
            }

            // Print cells on this row
            for(int i = 0; i <= m; i++){
                Score s = alignedTable[i][j];
                System.out.print(String.format("|%3d %-3c",
                        s.getPoints(), s.getChoice()));
            }
            printRowLines(m);
        }
    }

    // print the bottoms lines of the table's rows
    private static void printRowLines(int m){
        int totSpaces = (m + 1) * 8 + 1;
        System.out.print("\n");
        for(int i = 0; i <= totSpaces; i++){
            System.out.print("-");
        }
    }

    // Prints the best string for x and y
    private static void printBestStrings(){
        System.out.println("\n\nFinal x: " + bestXY[0]);
        System.out.println("Final y: " + bestXY[1]);
    }

    public String getBestX(){
        return bestXY[0];
    }

    public String getBestY(){
        return bestXY[1];
    }
}
