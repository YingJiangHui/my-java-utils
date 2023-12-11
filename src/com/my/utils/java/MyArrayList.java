package com.my.utils.java;

public class MyArrayList<E extends Object> {
    private static final int  DEFAULT_CAPACITY=10;
    private int size;
    private  int capacity;
    private  E[] store;

    /**
     * Default constructor, default capacity is 10
      */
    public MyArrayList(){
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.store = (E[]) new Object[this.capacity];
    }

    /**
     * Assign an initial capacity
     * @param capacity
     */
    public MyArrayList(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.store = (E[]) new Object[this.capacity];
    }
    public int capacity(){
        return this.capacity;
    }
    public int size (){
        return this.size;
    }

    public E get(int index){
        return store[index];
    }

    public void set(int index, E element){
        checkIndexBound(index);
        store[index] = element;
    }

    public boolean add(E element){
        return add(size,element);
    }
    public E remove(int index){
        E[] newArray = (E[]) new Object[this.capacity];
        E e = this.store[index];

        for(int i = 0;i<index;i++){
            newArray[i] = this.store[i];
        }

        for(int i = index;i<size-1;i++){
            newArray[i] = this.store[i+1];
        }
        size=size-1;
        this.store = newArray;
        return e;
    }

    public boolean add(int index,E element){
        // When need expand capacity of array
        if(size == capacity){
            expandCapacity(this.capacity*2);
        }
        for (int i = size;i>index;i--){
            this.store[i] = this.store[i-1];
        }
        this.store[index]=element;
        size+=1;
        return true;
    }

    public boolean isEmpty(){
        return this.size==0;
    }
    public int indexOf(E element){
        for(int i =0 ;i<this.size;i++){
            if(this.store[i].equals(element)){
                return i;
            }
        }
        return -1;
    }
    public boolean contains(E element){
        return indexOf(element)>=0;
    }

    private void checkIndexBound(int index){
        if(index<0||index>=this.size){
            throw new IndexOutOfBoundsException(String.format("size: %d,index: %d",this.size,index));
        }
    }

    public static void print(MyArrayList arrayList){
        for(int i = 0;i<arrayList.size();i++){
            System.out.println("element: "+arrayList.get(i));
        }
    }
    private void expandCapacity(int capacity){
        int c = Math.max(capacity,DEFAULT_CAPACITY);
        E[] newStore = (E[]) new Object[c];
        for(int i = 0;i<this.store.length;i++){
            newStore[i] = this.store[i];
        }
        this.capacity = c;
        this.store = newStore;
    }

    public static void main(String[] args){
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        for(int i = 0;i<21;i++){
            myArrayList1.add(i);
        }
        MyArrayList.print(myArrayList1);
        System.out.println(String.format("After added 21 elements, size is %d, capacity is %d",myArrayList1.size(),myArrayList1.capacity()));
        for(int i = 0;i<10;i++){
            myArrayList1.remove(0);
        }
        MyArrayList.print(myArrayList1);
        System.out.println(String.format("After removed 10 elements, size is %d, capacity is %d",myArrayList1.size(),myArrayList1.capacity()));
        System.out.println(String.format("index of the 17 element is " + myArrayList1.indexOf(17)));
        System.out.println(String.format("index of the 100 element is " + myArrayList1.indexOf(100)));

    }
}
