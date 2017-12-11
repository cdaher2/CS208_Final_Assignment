import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Iterator<Player> iter;
    private JButton challenge;
    private JLabel winner;
    private Player currentplayer;
    private Player challenger;
    private int currentPositionX;
    private int currentPositionY;
    private Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE, Color.PINK, Color.ORANGE, Color.RED, Color.GRAY};
    private Room currentRoom;
    private volatile int rpsResult = -2;
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        
        challenge = new JButton("Challenge");
        challenge.setLocation(0,0);
        challenge.setSize(100,20);
        rooms.get(0).add(challenge);
        challenge.addMouseListener(this);
        challenge.setVisible(false);
        fillRoomsWithPlayers();
        
            winner = new JLabel();
            winner.setSize(100,20);
            rooms.get(4).add(winner);
        
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
     * Eradicates a player from existance
     */
    public void removePlayer(Player p) {
        iter = players.iterator();
        while (iter.hasNext()) {
            Player str = iter.next();
            if (str.equals(p)) {
                iter.remove();
                rooms.get(p.getRoom()).remove(p);
            }
        }
        
        if(p.equals(currentplayer)) {
            currentplayer = players.get(0);
            currentplayer.setColor(new Color(193,23,120));
        }
        checkWinner();
    }
    
    /**
     * Checks to see who has won the game, if anyone has
     */
    public void checkWinner() {
        if(players.size() == 1) {
            winner.setText("You won, " + players.get(0).getName() +"!");
            winner.setVisible(true);
        }
    }
    
    /**
     * Creates 5 players and adds them to rooms
     */
    public void fillRoomsWithPlayers()
    {
        for(int i = 0; i < 5; i++) {
            Player player = new Player("Player"+(i+1), i);
            player.addMouseListener(this);
            players.add(player);
            addPlayerToRoom(player.getRoom(),player);
            currentPositionX = 0;
            currentPositionY = 0;
        }
        currentplayer = players.get(0);
        currentplayer.setToSelectColor();
    }
    
    /**
     * Allows the current player to challenge another to a game
     * @param p - Player to be challenged
     */
    public void challengePlayer(Player p) {
        challenger = p;
        challenge.setVisible(true);
    }
    
    /**
     * Checks to see if any other players are near currentplayer
     */
    public void detectPlayersInRange() {
        for(Player player : players) {
            if(currentplayer.equals(player) == false && currentplayer.getRoom() == player.getRoom()) {
                if((currentplayer.getX() >= player.getX() - 20)
                    && (currentplayer.getX() <= player.getX() + 20)
                    && (currentplayer.getY() >= player.getY() - 20)
                    && (currentplayer.getY() <= player.getY() + 20)) {
                    challengePlayer(player);
                }
            }
        }
    }
    /**
     * Removes a player based on game results
     */
    public void finishHim() {
        switch(rpsResult) {
            case 0: //do nothing
                break;
            case 1:
                //current player won, remove challenger
                removePlayer(challenger);
                break;
            case -1:
                //current player lost, remove currentplayer
                removePlayer(currentplayer);
                break;
            default: //do nothing
                break;
        }
    }
   
    /**
     * Sets result of rock, paper, scissors game
     * @param r - numeric value of result
     */
    public void setResult(int r) {
        rpsResult = r;
    }
    
    /**
     * The player can be selected via mouse click
     * The room the player is in can be selected via mouse click
     * @param e - The mouse being clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if((e.getSource()) instanceof Player) {
            currentplayer.setDefaultColor();
            currentplayer = (Player) (e.getSource());
            currentPositionX = currentplayer.getX();
            currentPositionY = currentplayer.getY();
            currentplayer.setToSelectColor();
            repaint();
        }
        if((e.getSource()) instanceof Room) {
            currentRoom = (Room) (e.getSource());
            (rooms.get(currentplayer.getRoom())).remove(currentplayer);
            currentplayer.setRoom(currentRoom.getNumber());
            addPlayerToRoom(currentRoom.getNumber(), currentplayer);
            detectPlayersInRange();
            repaint();
        }
        
        if((e.getSource()).equals(challenge)) {
            RockPaperScissors rps = new RockPaperScissors(this);
            challenge.setVisible(false);
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
     * W, A, S, and D used to control player
     * @param e - key being pressed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        detectPlayersInRange();
        if(keyCode == KeyEvent.VK_W) {
            currentPositionY = currentPositionY - 5;
            //needs to have boundaries
            currentplayer.setY(currentPositionY);
            currentplayer.setBounds(currentplayer.getX(),currentplayer.getY(),20,20);
            repaint();
        }
        else if(keyCode == KeyEvent.VK_D) {
            currentPositionX = currentPositionX + 5;
            currentplayer.setX(currentPositionX);
            currentplayer.setBounds(currentplayer.getX(),currentplayer.getY(),20,20);
            repaint();
        }
        else {
            e.consume();
        }
        if(keyCode == KeyEvent.VK_A) {
            currentPositionX = currentPositionX - 5;
            currentplayer.setX(currentPositionX);
            currentplayer.setBounds(currentplayer.getX(),currentplayer.getY(),20,20);
            repaint();
        }
        else if(keyCode == KeyEvent.VK_S) {
            currentPositionY = currentPositionY + 5;
            currentplayer.setY(currentPositionY);
            currentplayer.setBounds(currentplayer.getX(),currentplayer.getY(),20,20);
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