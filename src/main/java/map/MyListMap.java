package map;

import java.util.Map;

public class MyListMap<K, V> {
    private final Node<K, V> head, tail;
    private int size;

    public MyListMap() {
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
}
