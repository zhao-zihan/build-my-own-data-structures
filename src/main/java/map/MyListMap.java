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
}
