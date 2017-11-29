import javax.swing.JPanel;
/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Room extends JPanel
{
    private int number;

    /**
     * Constructor for objects of class Room
     */
    public Room()
    {
        number = 0;
    }

    /**
     * Constructor for objects of class Room
     */
    public Room(int num)
    {
        number = num;
    }
    
    public int getNumber() {return number;}
    public void setNumber(int n) {number = n;}
}
