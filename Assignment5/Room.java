import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import java.io.*;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (0.1)
 */
public class Room extends JPanel
{
    private Color transparent = new Color(34,23,42,0);
    protected class Door extends JPanel
    {
        private String direction;
        private ImageIcon image;
        private JLabel label;
        private URL file;
        
        public Door()
        {
            image = new ImageIcon("leftdoor.png");
            setFile("leftdoor.png");
            label = new JLabel(image);
            setBackground(transparent);
            //setBounds(0, 0, 22, 22);
        }
        
        public Door(String p)
        {
            direction = p;
            image = new ImageIcon();
            label = new JLabel(image);
            setBackground(transparent);
            //setBounds(0, 0, 22, 22);
        }
        
        public void setDirection(String d)
        {
            direction = d;
        }
        
        public String getDirection()
        {
            return direction;
        }
        
        /**
         * Returns the name of the door's image file
         */
        public URL getFile()
        {
            return file;
        }
        
        /**
         * Sets the image displayed
         * @param x -> The desired image to display
         */
        public void setFile(String pic)
        {
            file = this.getClass().getResource(pic);
            try {
                BufferedImage img = ImageIO.read(file);
                image.setImage(img);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        private void drawDoor(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
        
            switch(direction) {
                case "left": setFile("leftdoor.png");
                case "right": setFile("rightdoor.png");
                case "up": setFile("upperdoor.png");
                case "down": setFile("lowerdoor.png");
            }
            g2d.setColor(new Color(20,30,34,0));
            getInsets().set(0, 0, 0, 0);
        }
        
        /**
         * Paints the door depending on direction
         * @param g - Graphical element
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawDoor(g);
        }
    }
    private ImageIcon wall;
    private URL file;
    private JPanel inner;
    private int number;
    private JLabel label;
    private MHashTable<String, Player> a;
    
    /**
     * Constructor for objects of class Room
     */
    public Room()
    {
        setLayout(null);
        setBounds(0, 0, 100, 100);
        number = 0;
        file = this.getClass().getResource("wall.png");
        wall = new ImageIcon(file);
        label = new JLabel(wall);
        label.setLayout(null);
        label.setBounds(0, 0, 100, 100);
        add(label);
        
        
        //inner part of room
        inner = new JPanel();
        inner.setBackground(transparent);
        inner.setSize(72,72);
        inner.setLocation(14,14);
        add(inner);
    }

    /**
     * Constructor for objects of class Room
     * @param num - int room number
     */
    public Room(int num)
    {
        setLayout(null);
        setBounds(0, 0, 100, 100);
        Door door = new Door();
        add(door);
        number = num;
        file = this.getClass().getResource("wall.png");
        wall = new ImageIcon(file);
        label = new JLabel(wall);
        label.setLayout(null);
        label.setBounds(0, 0, 100, 100);
        add(label);
        
        //Inner part of room
        inner = new JPanel();
        inner.setBackground(transparent);
        inner.setSize(72,72);
        inner.setLocation(14,14);
        add(inner);
    }
    
    /**
     * Gets the room number
     * @return - this room's assigned number
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Sets the room number
     * @param n - The desired room number
     */
    protected void setNumber(int n) {
        number = n;
    }
    
    /**
     * Returns the file being used for walls
     * @return - URL of file
     */
    public URL getFile() {
        return file;
    }
    
    /**
     * Adds a JPanel object to the inner part of Room
     * @param p - JPanel object
     */
    public void addToInnerBounds(JPanel p) {
        inner.add(p);
    }
    
    /**
     * Returns the inner JPanel object
     * @return - inner JPanel
     */
    public JPanel getInnerBounds() {
        return inner;
    }
    
    /**
     * Sets the image displayed
     * @param x -> The desired image to display
     */
    public void setFile(String pic)
    {
        file = this.getClass().getResource(pic);
        try {
            BufferedImage img = ImageIO.read(file);
            wall.setImage(img);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns whether or not this room is the same as another room
     * @return - boolean value true or false
     */
    public boolean equals(Room room) {
        if(room instanceof Room){
            if(this.number == room.number)
                { return true; }
        }
        return false;
    }
}