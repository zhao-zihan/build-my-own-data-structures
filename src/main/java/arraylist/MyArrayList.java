package arraylist;

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
        //Note: the
        data[size] = e;
        size++;
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
}
