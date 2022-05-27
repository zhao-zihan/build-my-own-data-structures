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
}