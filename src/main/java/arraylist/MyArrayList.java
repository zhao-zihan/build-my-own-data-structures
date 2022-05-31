package arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList<E> implements Iterable<E> {
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

    //Add an element at a given index
    public void add(int index, E element) {
        checkPositionIndex(index);
        //move all the elements from the index position one step backward to the right
        //data[index..] -> data[index + 1..]
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
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
        data[--size] = null;
        return removedValue;
    }

    public E remove(int index) {
        checkElementIndex(index);
        //move all elements after the index position one step forward to the left
        //data[index + 1..] -> data[index..]
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        E removedValue = data[index];
        data[--size] = null;
        return removedValue;
    }

    public E removeFirst() {
        return remove(0);
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            @Override
            public boolean hasNext() {
                return position != size;
            }

            @Override
            public E next() {
                return data[position++];
            }
        };
    }

    /**
     * Simple tests
     * @param args
     */
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>();
        System.out.print("Initial elements are: \n");
        for (int i = 0; i <= 5; i++) {
            arr.addLast(i);
            System.out.print(i + " ");
        }

        arr.remove(3);
        arr.add(1, 9);
        int val = arr.removeLast();
        System.out.println("\nRemoved last element is: " + val);

        System.out.println("Modified elements are: ");
        for (int i = 0; i < arr.size; i++) {
            System.out.print(arr.get(i) + " ");
        }

        System.out.println("\nPrint elements using iterator: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
