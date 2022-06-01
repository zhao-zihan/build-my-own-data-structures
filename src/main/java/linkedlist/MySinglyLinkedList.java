package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySinglyLinkedList<E> implements Iterable{
    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    public MySinglyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        this.size = 0;
    }

    /****** Add ******/

    public void addFirst(E element) {
        Node<E> inserted = new Node<>(element);
       Node<E> next = head.next;

        inserted.next = next;
        head.next = inserted;

        size++;
    }

    public void addLast(E element) {
        Node<E> inserted = new Node<>(element);
        Node<E> prev;
        if (size - 1 >= 0) {
            prev = getNode(size - 1);
        } else {
            prev = head;
        }

        inserted.next = tail;
        prev.next = inserted;

        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> curr = getNode(index);
        Node<E> inserted = new Node<>(element);
        Node<E> prev;
        if (index - 1 >= 0) {
            prev = getNode(index - 1);
        } else {
            prev = head;
        }

        prev.next = inserted;
        inserted.next = curr;

        size++;
    }

    /****** Delete ******/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> removed = head.next;
        head.next = removed.next;

        removed.next = null;

        size--;

        return removed.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> removed;
        if (size < 2) {
            removed = head.next;
            head.next = tail;
        } else {
            removed = getNode(size - 1);
            Node<E> prev = getNode(size - 2);
            prev.next = tail;
        }

        removed.next = null;

        size--;

        return removed.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        if (index < 1) {
            return removeFirst();
        } else {
            Node<E> curr = getNode(index);
            Node<E> next = curr.next;
            Node<E> prev = getNode(index - 1);
            prev.next = next;
            curr.next = null;
            size--;
            return curr.val;
        }
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
        Node<E> last = getNode(size - 1);
        return last.val;
    }

    public E get(int index) {
        checkElementIndex(index);
        Node<E> node = getNode(index);
        return node.val;
    }

    /****** Update ******/

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> node = getNode(index);
        E previousValue = node.val;
        node.val = element;
        return previousValue;
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

    /**
     * This function is to return a node at a given index,
     * this will help with all operations that work on a specific element
     * @param index
     * @return
     */
    private MySinglyLinkedList.Node<E> getNode(int index) {
        MySinglyLinkedList.Node<E> pivot = head.next;
        for (int i = 0; i < index; i++) {
            pivot = pivot.next;
        }
        return pivot;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            MySinglyLinkedList.Node<E> curr = head.next;
            @Override
            public boolean hasNext() {
                return curr != tail;
            }

            @Override
            public Object next() {
                E val = curr.val;
                curr = curr.next;
                return val;
            }
        };
    }
}
