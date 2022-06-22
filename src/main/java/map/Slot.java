package map;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Slot<K, V> {
    private final Node<K, V> head, tail;
    private int size;

    public Slot() {
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        head.next = tail;
        this.size = 0;
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldVal = this.value;
            this.value = value;
            return oldVal;
        }
    }

    /****** Add/Update ******/

    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        //check if there is an existing node
        //if yes, update the value
        Node<K, V> p = getNode(key);
        if (p != null) {
            V oldVal = p.value;
            p.value = val;
            return oldVal;
        }

        //if not, make a new node and insert it to the front
        Node<K, V> x = new Node<>(key, val);
        x.next = head.next;
        head.next = x;
        size++;
        return null;
    }

    /****** Delete ******/

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        Node<K, V> prev = head;
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            if (key.equals(p.key)) {
                prev.next = p.next;
                size--;
                return p.value;
            }
            //set prev always equal to the previous node of p
            prev = p;
        }
        return null;
    }

    /****** Read ******/

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Node<K, V> p = getNode(key);
        if (p == null) {
            return null;
        }
        return p.value;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return getNode(key) != null;
    }

    private Node<K, V> getNode(K key) {
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            if (key.equals((p.key))) {
                return p;
            }
        }
        return null;
    }

    /****** Tools ******/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //return keySet
    public List<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            keyList.addLast(p.key);
        }
        return keyList;
    }

    //return all entries
    public List<Map.Entry<K, V>> entries() {
        LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
        //node has implemented Entry interface
        Node<K, V> p = head.next;
        while (p != tail) {
            entryList.addLast(p);
            p = p.next;
        }
        return entryList;
    }
}
