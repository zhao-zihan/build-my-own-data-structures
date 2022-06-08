package queue;

import linkedlist.MyLinkedList;

public class MyLinkedQueue<E> {
    private MyLinkedList<E> list = new MyLinkedList<>();

    //Add an element from the back
    public void offer(E e) {
        list.addLast(e);
    }

    //Pop an element from the beginning
    public E poll() {
        return list.removeFirst();
    }

    //Get the first element
    public E peekFirst() {
        return list.getFirst();
    }

    //Get the last element
    public E peekLast() {
        return list.getLast();
    }
}
