package map;

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

    }
}
