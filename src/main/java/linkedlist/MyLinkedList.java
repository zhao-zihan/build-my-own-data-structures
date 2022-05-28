package linkedlist;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    /****** Add ******/

    public void addFirst(E element) {
        Node<E> inserted = new Node<>(element);
        Node<E> next = head.next;

        inserted.next = next;
        inserted.prev = head;

        head.next = inserted;
        next.prev = inserted;

        size++;
    }

    public void addLast(E element) {
        Node<E> inserted = new Node<>(element);
        Node<E> prev = tail.prev;

        inserted.next = tail;
        inserted.prev = prev;

        tail.prev = inserted;
        prev.next = inserted;

        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        size++;
    }

    /****** Delete ******/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> removed = head.next;
        head.next = removed.next;
        removed.next.prev = head;

        removed.next = removed.prev = null;

        size--;

        return removed.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> removed = tail.prev;
        removed.prev.next = tail;
        tail.prev = removed.prev;

        removed.next = removed.prev = null;

        size--;

        return removed.val;
    }

    /****** Read ******/

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    /****** Tools ******/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isValidPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * This function is to check if a given index is accessible for searching an element
     * @param index
     */
    private void checkElementIndex(int index) {
        if (!isValidElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * This function is to check if a given index is accessible for insertion
     * @param index
     */
    private void checkPositionIndex(int index) {
        if (!isValidPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
