package map;

import java.util.Map;

public class MyHashMap1<K, V> {
    private int size;
    private Slot<K, V>[] table;
    private static final int INIT_CAP = 4;

    public MyHashMap1() {
        this(INIT_CAP);
    }

    public MyHashMap1(int initCapacity) {
        size = 0;
        table = (Slot<K, V>[]) new Slot[initCapacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new Slot<>();
        }
    }

    /****** Add/Update ******/

    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (size >= table.length * 0.75) {
            resize(2 * table.length);
        }
        Slot<K, V> slot = table[hash(key)];
        if (!slot.containsKey(key)) {
            size++;
        }
        return slot.put(key, val);
    }

    /****** Delete ******/

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Slot<K, V> slot = table[hash(key)];
        if (slot.containsKey(key)) {
            size--;
            return slot.remove(key);
        }
        return null;
    }

    /****** Read ******/

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        Slot<K, V> slot = table[hash(key)];
        return slot.get(key);
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Slot<K, V> slot = table[hash(key)];
        return slot.containsKey(key);
    }


    /****** Tools ******/

    //convert key to a valid table index
    //[0, table.length - 1]
    private int hash(K key) {
        //since the hashCode function returns an int number that could be zero
        //use & operator to convert any number to positive
        //0x7fffffff: 0111 1111 1111 ... 1111
        // & operator requires two 1s to get 1, else get 0
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCap) {
        MyHashMap1<K, V> newMap = new MyHashMap1<>(newCap);

        //remove all entries in the current table to the new map
        for (Slot<K, V> slot : table) {
            for (Map.Entry<K, V> entry : slot.entries()) {
                K key = entry.getKey();
                V val = entry.getValue();
                newMap.put(key, val);
            }
        }

        //re-assign the value of table
        this.table = newMap.table;
    }
}
