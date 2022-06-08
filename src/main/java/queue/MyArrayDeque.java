package queue;

public class MyArrayDeque<E> {
    private int size;
    private E[] data;
    private final static int INIT_CAP = 2;

    private int first, last;

    public MyArrayDeque(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        //set first & last index to 0
        //[first, last)
        first = last = 0;
    }

    //in case no initial capacity passed in
    public MyArrayDeque() {
        this(INIT_CAP);
    }

    /****** Add ******/
    
    public void addFirst(E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }
    }

    public void addLast(E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }
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
        // first-----last
        //---last   first---
        //in case first has been moved behind last, use %
        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }
        first = 0;
        last = size;
        data = temp;
    }
}
