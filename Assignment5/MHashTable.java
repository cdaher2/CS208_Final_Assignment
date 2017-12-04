import java.util.ArrayList;

public class MHashTable<K, V> {
	
	private ArrayList<Node<K, V>> master;
	
	public MHashTable(){
		master = new ArrayList<>();
	}
	
	public void put (K k, V v) {
		
	}
	
	protected static class Node<K, V> {
		public K key;
		public V value;
		
		public Node(K k, V v) {
			key = k;
			value = v;
		}
		
	}
	
}
