/**
 * MHashTable.java
 * A Hashtable class for storing Player instances. Hashes should be stored as Strings, and are generated from a combination of 
 * the player's name and timestamp, thus allowing for two players to have the same name.
 * 
 * @author Christian Daher
 */

import java.util.LinkedList;
import java.util.Hashtable;
public class MHashTable<K, V>  extends Hashtable<K, V>{

    private Node<K, V>[] master;
    private int CAPACITY = 101;
    
    public MHashTable(){
        master = new Node[CAPACITY];
    }
    
    public void place(K k, V v) {
        int i = k.hashCode() % CAPACITY;
        if (master[i] == null) {
            master[k.hashCode() % CAPACITY] = new Node<K, V>(k, v);
        }
        else {
            boolean f = true;
            while (f) {
                if (master[i] == null) {
                    f = false;
                    master[i] = new Node<K, V>(k, v);
                }
                i++;
            }
        }
    }

    public V getValue(K k) {
        V v = null;
        Node<K, V> temp;
        int i = k.hashCode() % CAPACITY;
        boolean f = true;
        while (f && (i < CAPACITY - 1)) {
            temp = master[i];
            if (temp.key == k.hashCode()) {
                f = false;
                    v = (V) temp.value;
            }
            i++;
        }
        return v;
    }

    protected static class Node<K, V> {
        public int key;
        public V value;
        
        public Node(K k, V v) {
            key = k.hashCode();
            value = v;
        }
    }
}