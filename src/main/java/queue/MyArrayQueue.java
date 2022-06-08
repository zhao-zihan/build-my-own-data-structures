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

    /****** Tools ******/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }
        first = 0;
        last = size;
        data = temp;
    }
}
