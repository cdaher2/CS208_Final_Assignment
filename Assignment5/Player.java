import javax.swing.JPanel;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends JPanel
{
    private String name;
    private int room;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        name = "Player";
        room = 1;
    }

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, int room)
    {
        this.name = name;
        this.room = room;
        setSize(20,20);
    }
    
    
    /**
     * 
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * 
     */
    public int getRoom() {
        return room;
    }
    
    /**
     * 
     */
    public void setRoom(int r) {
         room = r;
    }
}
