import java.util.Arrays;

public class MyList<T> {

    T[] myList;     //T array list

    public MyList() {   //No parameter constructor
        this.myList = (T[]) new Object[10];
    }

    public MyList(int capacity){    //Constructor with capacity
        this.myList = (T[]) new Object[capacity];
    }

    public int size(){  //Size of array
        int count = 0;
        for (T element : myList) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    public int getCapacity() {
        return myList.length;
    }   //Get capacity

    public void add(T data){    //Add element to the array
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] == null) {    //If the index is null, then add basically
                myList[i] = data;
                break;
            } else if (myList[i] != null && i == myList.length - 1) {       //Else copy the array to the new array with new capacity
                myList = Arrays.copyOf(myList, myList.length * 2);
                myList[myList.length / 2] = data;
                break;
            }
        }
    }

    public T get(int index){    //Get the element of that index
        try {
            if (myList[index] != null) {
                return myList[index];
            } else return null;
        } catch (Exception ignored) {
            return null;
        }
    }

    public T remove(int index) {    //Remove the element from the array
        T value;
        try {
            value=get(index);
            if (myList[index] != null) {
                if (size() - index >= 0) System.arraycopy(myList, index + 1, myList, index, size() - index);
            }
        } catch (Exception ignored) {
            return null;
        }
        return value;
    }

    public void set(int index, T data) {    //Set the data to the index
        try {
            if (myList[index] != null) {
                myList[index] = data;
            }
        } catch (Exception ignored) {
            System.out.println("Wrong operation ! ");
        }
    }

    public String toString() {  //Print elements according to given homework
        if (size() > 0) {
            StringBuilder str = new StringBuilder("[");
            for (int i = 0; i < size(); i++) {
                if (i == (size() - 1)) {
                    str.append(myList[i]).append("]");
                } else str.append(myList[i]).append(",");

            }
            return str.toString();
        }
        return "[]";
    }

    public int indexOf(T data) {    //Get the index of specific element
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (myList[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T data) {    //Get the last index of specific element
        int index = -1;
        for (int i = size() - 1; i >= 0; i--) {
            if (myList[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean isEmpty() {  //Boolean empty
        return size() == 0;
    }

    public T[] toArray() {  //To copy the array
        return Arrays.copyOfRange(myList, 0, size());
    }

    public void clear() {   //Clear the array
        this.myList = (T[]) new Object[10];
    }

    public MyList<T> subList(int start, int finish) {   //Return an array between two given indexes
        MyList<T> newList = new MyList<>((finish - start + 1));
        for (int i = start; i <= finish; i++) {
            newList.add(myList[i]);
        }
        return newList;
    }

    public boolean contains(T data) {   //Boolean contains method
        boolean isIt = false;
        for (int i = 0; i < size(); i++) {
            if (myList[i] == data) {
                isIt = true;
                break;
            }
        }
        return isIt;
    }


}
