import java.awt.*;
import java.util.HashMap;

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
    private Container contents;
    private HashMap<Player, Integer> map;
    private ArrayList<JPanel> rooms;
    private ArrayList<Player> players;
    private Player player;
    private Player currentplayer;
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE, Color.PINK, Color.ORANGE, Color.RED, Color.GRAY};
    private int numberOfRooms;
    /**
     * Constructor for objects of class GUIEnvironment
     */
    public GUIEnvironment()
    {
        map = new HashMap<Player, Integer>();
        rooms = new ArrayList<JPanel>();
        numberOfRooms = 9;
        createEnvironment();
    }

    /**
     * 
     *
     */
    public void createEnvironment()
    {
        contents = getContentPane();
        contents.setLayout(new GridLayout(3,3));
        for(int i = 0; i< 9; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.setBackground(colors[i]);
            rooms.add(panel);
            contents.add(panel);
        }
        player = new Player("Player1", 1);
        player.addMouseListener(this);
        player.setBackground(Color.BLACK);
        Player player2 = new Player("Player2", 2);
        player2.addMouseListener(this);
        addPlayerToRoom(player2.getRoom(), player2);
        addPlayerToRoom(player.getRoom(), player);
        
        setSize(300, 300);
        setLocationRelativeTo(null); //Centers window
        setVisible(true);
    }
    
    /**
     * Adds player to a room
     * @param roomNumber - the room a player to add a player to
     */
    public void addPlayerToRoom(int roomNumber, Player p) {
        (rooms.get(roomNumber)).add(p);
    }
    
    /**
     * 
     * 
     */
    public void playerOnline()
    {
        
    }
    
    /**
     * W, A, S, and D used
     * @param e - key being pressed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) {
            
        }
        else if(keyCode == KeyEvent.VK_D) {
            
        }
        else {
            e.consume();
        }
        if(keyCode == KeyEvent.VK_A) {
            
        }
        else if(keyCode == KeyEvent.VK_S) {
            
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
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if((e.getSource()) instanceof Player) {
            currentplayer = (Player) (e.getSource());
            (rooms.get(currentplayer.getRoom())).remove(currentplayer);
            player.setRoom(currentplayer.getRoom()+2);
            
            (rooms.get(currentplayer.getRoom())).add(currentplayer);
            
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
}