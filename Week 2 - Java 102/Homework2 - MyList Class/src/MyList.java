public class MyList<T> {

    Object[] myList;
    private int size = 0;
    private int capacity;

    public MyList() {
        this.myList = (T[]) new Object[10];
        setCapacity(10);

    }

    public MyList(int capacity){
        this.myList = (T[]) new Object[capacity];
        setCapacity(capacity);
    }

    public int size(){
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
