
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
    }
}