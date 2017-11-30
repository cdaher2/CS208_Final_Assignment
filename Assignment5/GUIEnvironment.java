import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
/**
 * All GUI elements go here.
 *
 * @author (Catherine Denis)
 * @version (0.1)
 */
public class GUIEnvironment extends JFrame implements KeyListener, MouseListener
{
    private Random random;
    private Container contents;
    private HashMap<Player, Integer> map;
    private ArrayList<Room> rooms;
    private ArrayList<Player> players;
    private Player currentplayer;
    private int currentPositionX;
    private int currentPositionY;
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE, Color.PINK, Color.ORANGE, Color.RED, Color.GRAY};
    private Room currentRoom;
    /**
     * Constructor for objects of class GUIEnvironment
     */
    public GUIEnvironment()
    {
        random = new Random();
        map = new HashMap<Player, Integer>();
        rooms = new ArrayList<Room>();
        players = new ArrayList<Player>();
        createEnvironment();
    }

    /**
     * Puts together all GUI elements for display
     */
    public void createEnvironment()
    {
        contents = getContentPane();
        contents.setLayout(new GridLayout(3,3));
        for(int i = 0; i< 9; i++) {
            Room panel = new Room(i);
            panel.setLayout(null);
            panel.addMouseListener(this);
            panel.setBackground(colors[i]);
            rooms.add(panel);
            contents.add(panel);
        }
        itsRainingMen();
        
        addKeyListener(this);
        setSize(300, 300);
        setLocationRelativeTo(null); //Centers window
        setVisible(true);
    }
    
    /**
     * Adds player to a room
     * @param roomNumber - the room a player to add a player to
     * @param p - Player to add to room
     */
    public void addPlayerToRoom(int roomNumber, Player p) {
        (rooms.get(roomNumber)).add(p);
    }
    
    /**
     * Adds players and adds them to rooms
     */
    public void itsRainingMen()
    {
        for(int i = 0; i < 5; i++) {
            Player player = new Player("Player"+(i+1), i);
            player.addMouseListener(this);
            players.add(player);
            addPlayerToRoom(player.getRoom(),player);
            currentPositionX = 0;//change to be related to cell/jpanel
            currentPositionY = 0;
        }
        currentplayer = players.get(0);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if((e.getSource()) instanceof Player) {
            currentplayer.setDefaultColor();
            currentplayer = (Player) (e.getSource()); //assign currentplayer to player clicked
            currentplayer.setColor(new Color(34,121,23));
            repaint();
        }
        if((e.getSource()) instanceof Room) {
            currentRoom = (Room) (e.getSource());
            (rooms.get(currentplayer.getRoom())).remove(currentplayer);
            currentplayer.setRoom(currentRoom.getNumber());
            addPlayerToRoom(currentRoom.getNumber(), currentplayer);
            repaint();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    /**
     * W, A, S, and D used
     * @param e - key being pressed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) {
            currentPositionY = currentPositionY - 5;
            //needs to have boundaries
            currentplayer.setBounds(currentPositionX,currentPositionY,20,20);
            repaint();
        }
        else if(keyCode == KeyEvent.VK_D) {
            currentPositionX = currentPositionX + 5;
            currentplayer.setBounds(currentPositionX,currentPositionY,20,20);
            repaint();
        }
        else {
            e.consume();
        }
        if(keyCode == KeyEvent.VK_A) {
            currentPositionX = currentPositionX - 5;
            currentplayer.setBounds(currentPositionX,currentPositionY,20,20);
            repaint();
        }
        else if(keyCode == KeyEvent.VK_S) {
            currentPositionY = currentPositionY + 5;
            currentplayer.setBounds(currentPositionX,currentPositionY,20,20);
            repaint();
        }
        else {
            e.consume();
        }
        contents.setFocusable(true);
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        contents.setFocusable(true);
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        contents.setFocusable(true);
    }
}