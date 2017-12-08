
public class Asgn5 {
    public static void main(String[] args) throws InterruptedException {
    	//GUIEnvironment g = new GUIEnvironment();
    	MHashTable<Player, Player> mh = new MHashTable();
    	Player p1 = new Player("Andre", 0);
    	Player p2 = new Player("Charles", 0);
    	System.out.println(p1.hashCode() + " " + p2.hashCode());
    	
    	mh.put(p1, p1);
    	mh.put(p2, p2);
    	
    	System.out.println(mh.get(p1).getName() + " " + mh.get(p2).getName());
    	
    	//Test for Player key and Room value
    	MHashTable<Player, Room> hTable = new MHashTable();
    	Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",1);
        Player player3 = new Player("Player3",2);
        
    	System.out.println("Player 1: " + player1.hashCode());
    	System.out.println("Player 2: " + player2.hashCode());
    	System.out.println("Player 3: " + player3.hashCode());
        
        Room room1 = new Room();
        Room room2 = new Room(1);
        Room room3 = new Room(2);
        
        System.out.println("Room 1: " + room1.getNumber());
        System.out.println("Room 2: " + room2.getNumber());
        System.out.println("Room 3: " + room3.getNumber());
        
        hTable.put(player1, room1);
        hTable.put(player2, room2);
        hTable.put(player3, room3);
        
        System.out.println(hTable.get(player1).getNumber() + " " + hTable.get(player2).getNumber());
    }
}