package queue;

import java.util.NoSuchElementException;

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
        if (first == 0) {
            first = data.length - 1;
        } else {
            first--;
        }
        data[first] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[last] = e;
        last++;
        if (last == data.length) {
            last = 0;
        }
        size++;
    }

    /****** Delete ******/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        E oldVal = data[first];
        data[first] = null;
        first++;
        if (first == data.length) {
            first = 0;
        }
        size--;
        return oldVal;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        if (last == 0) {
            last = data.length - 1;
        } else{
            last--;
        }

        E oldVal = data[last];
        data[last] = null;
        size--;
        return oldVal;
    }

    /****** Read ******/

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[first];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (last == 0) {
            return data[data.length - 1];
        }
        return data[last - 1];
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
