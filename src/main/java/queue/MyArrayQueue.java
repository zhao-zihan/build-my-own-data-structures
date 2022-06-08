package queue;

public class MyArrayQueue<E> {
    private int size;
    private E[] data;
    private final static int INIT_CAP = 1;
    private int first, last;

    public MyArrayQueue(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        first = last = 0;
    }

    public MyArrayQueue() {
        this(INIT_CAP);
    }
}
