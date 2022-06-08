package stack;

import linkedlist.MyLinkedList;

public class MyLinkedStack<E> {
    private MyLinkedList<E> list = new MyLinkedList<E>();

    public void push(E e) {
        list.addLast(e);
    }

    public E pop() {
        return list.removeLast();
    }

    public E peek() {
        return list.getLast();
    }
}
