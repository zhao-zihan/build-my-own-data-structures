package arraylist;

import java.util.NoSuchElementException;

public class MyArrayList<E> {
    //Use an array as the base
    private E[] data;
    //Use an integer size to store the actual number of elements in the array
    private int size;
    //Set initial capacity to 1
    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    /****** Add ******/

    //Add an element from the end
    public void addLast(E e) {
        int cap = data.length;
        //check if there is enough space for appending,
        //if not, double the capacity
        if (size == cap) {
            resize(cap * 2);
        }
        //Add the element at end of the array
        //Note: use size instead of data.length
        data[size++] = e;
    }

    /****** Delete ******/

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int cap = data.length;
        //resize data if size is too small but make sure
        // there are enough spaces left after resizing
        if (size < cap / 4) {
            resize(cap / 2);
        }
        //get the removed value, make its spot null then return the value
        E removedValue = data[size - 1];
        data[size - 1] = null;
        return removedValue;
    }

    /****** Read ******/

    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    /****** Update ******/

    public E set(int index, E element) {
        checkElementIndex(index);
        E previousValue = data[index];
        data[index] = element;
        return previousValue;
    }

    /****** Tools ******/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //resize data array to fit in more element
    private void resize(int newCapacity) {
        E[] temp = (E[]) new Object[newCapacity];
        //copy data to the new array
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    private boolean isValidElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isValidPositionIndex(int index) {
        //test if index <= size since it is possible to insert at the end
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
