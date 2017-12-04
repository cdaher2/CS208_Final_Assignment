import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;
/**
 * Write a description of class Player here.
 *
 * @author (Catherine Denis) & Christian Daher
 * @version (0.1)
 */
public class Player extends JPanel
{
	private int hash;
	private int timestamp = (int)new Date().getTime();
    private String name;
    private int room;
    private Color color;
    private Color defaultColor = new Color(145, 32, 121);
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        name = "Player";
        room = 1;
        color = defaultColor;
        this.makeHash();
    }

    /**
     * Constructor for objects of class Player
     * @param name - String desired name of the player
     * @param room - int room number the player starts in
     */
    public Player(String name, int room)
    {
        this.name = name;
        this.room = room;
        setSize(20,20);
        color = defaultColor;
        setBackground(new Color(145, 32, 121,0));
        this.makeHash();
    }
    
    /**
     * Gets the name of this player
     * @return - String the name of the player
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the player's name
     * @param n - String desired name of player
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * Get the room number that this player is in
     * @return - int room number
     */
    public int getRoom() {
        return room;
    }
    
    /**
     * Sets the room number that the player is in
     * @param r - The desired room number
     */
    public void setRoom(int r) {
         room = r;
    }
    
    /**
     * 
     */
    public void setColor(Color c) {color = c;}
    
    /**
     * 
     */
    public void setDefaultColor() {color = defaultColor;}
    
    /**
     * Draws the shape of the circle
     * @param g - Graphics component
     */
    private void drawAThing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(color);
        g2d.fillOval(0,0,20,20);
        getInsets().set(0, 0, 0, 0);
    }
    /**
     * Paints the graphical components of Resizable Circle
     * @param g - Graphical element
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAThing(g);
    }
    
    private void makeHash() {
    	hash = 1;
    	for (int i = 0; i < this.getName().length(); i++) {
    		hash = hash + (timestamp * (int)this.getName().charAt(i));
    	}
    	hash = Math.abs(hash);
    }
    
    @Override
    public int hashCode() {
    	return hash;
    }
    
}