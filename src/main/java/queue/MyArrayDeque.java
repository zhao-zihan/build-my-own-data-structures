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


}
