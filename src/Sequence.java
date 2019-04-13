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
    private static String x;
    private static String y;

    private static int m;
    private static int n;
    private static Score[][] alignedTable; // where scores are stored

    private static final int space = 2;

    // holds our best XY vals for testing
    private static String[] bestXY = new String[2];

    public Sequence(String inX, String inY){
      x = inX;
      y = inY;

      m = x.length();
      n = y.length();
      alignedTable = new Score[m + 1][n + 1];
    }

    // Takes the table and builds our best strings.
    private static void buildAnswer(){
        int i = m;
        int j = n;
        int xIndex = 0;
        int yIndex = 0;
        String xBest = "";
        String yBest = "";

        while(i != 0 || j != 0){
            char choice = alignedTable[i][j].getChoice();
            if(choice == 'n'){
              xBest += x.charAt(xIndex);
              yBest += y.charAt(yIndex);
              xIndex++;
              yIndex++;
              i--;
              j--;
            }
            else if(choice == 'x'){
              xBest += "_";
              yBest += y.charAt(yIndex);
              yIndex++;
              j--;
            }
            else{
              yBest += '_';
              xBest += x.charAt(xIndex);
              xIndex++;
              i--;
            }
        }
        bestXY[0] = xBest;
        bestXY[1] = yBest;
    }

    // Inserts base case values into the table
    private static void fillBaseCases(){
      for(int i = 0; i <= m; i++){
        alignedTable[i][0] = new Score(space * i, 'n');
      }
      for(int j = 0; j <= n; j++){
        alignedTable[0][j] = new Score(space * j, 'n');
      }
    }

    // return the correct point value based on the two characters given
    private static int getPairValue(char c, char d){
      if(c == d){
        return 0;
      }
      return 1; //default, c != d
    }

    // Builds the table of best scores
    public void startSequence(){
      fillBaseCases();

      for(int j = 1; j <= n; j++){
        char yChar = y.charAt(j - 1);
        for(int i = 1; i <= m; i++){
          char xChar = x.charAt(i - 1);
          int none = alignedTable[i - 1][j - 1].getPoints() + getPairValue(xChar, yChar);
          int xDash = alignedTable[i][j - 1].getPoints() + space;
          int yDash = alignedTable[i - 1][j].getPoints() + space;

          if(none < xDash && none < yDash){
            alignedTable[i][j] = new Score(none, 'n');
          }
          else if(xDash < yDash){
            alignedTable[i][j] = new Score(xDash, 'x');
          }
          else{
            alignedTable[i][j] = new Score(yDash, 'y');
          }
        }
      }

      buildAnswer();
      printTable();
      printBestStrings();
    }

    // Prints table containing all Scores
    private static void printTable(){
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
        printRowLines();

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
            printRowLines();
        }
    }

    // print the bottoms lines of the table's rows
    private static void printRowLines(){
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

    public String returnBestX(){
        return bestXY[0];
    }

    public String returnBestY(){
        return bestXY[1];
    }
}
