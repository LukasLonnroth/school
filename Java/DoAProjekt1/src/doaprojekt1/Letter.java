/*
 * Letter klassen måste implementera place() efter att vi byggt
 * någon form av spelplan
 */
package doaprojekt1;

/**
 *
 * @author lonnroka
 */
public class Letter {

    public String letter;
    public int points;
    public int column;
    public int row;

    Letter(String letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public void place(int row, int column) {

    }
}
