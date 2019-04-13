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
    private String x;
    private String y;
    private int m;
    private int n;

    private String[] bestXY = new String[2]; // holds our best XY vals for testing

    public Sequence(String inX, String inY){
        this.x = inX;
        this.y = inY;

        this.m = x.length();
        this.n = y.length();
    }

    // Takes the table and builds our best strings.
    private static String buildAnswer(){
        return "";
    }

    // Builds the table of best scores
    public void alignStrings(){

    }

    // Prints table containing all Scores
    public void printTable(){

    }

    public void printBestStrings(){

    }

    public String returnBestX(){
        return bestXY[0];
    }

    public String returnBestY(){
        return bestXY[1];
    }
}
