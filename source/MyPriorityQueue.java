/*
 * File Name:       MyPriorityQueue.java
 * Instructor:      Prof. Benjamin Goldberg
 *                  & Prof. Robert Grimm
 * Author:          Shen Li
 * UID:             N14361265
 * Department:      Computer Science
 * Note:            This file implements a priority queue.
 * */

import java.util.ArrayList;

/**
 * Implements a priority queue
 * @author Shen Li
 * */
public class MyPriorityQueue<E extends Comparable<? super E>> implements Comparable<MyPriorityQueue<E>>{
    private static final int    ROOT_INDEX = 0;
    private static final int    MAX_CAPACITY = 20000;
    private int                 capacity;
    private int                 currentSize;
    private ArrayList<E>        array;

    /**
     * Constructs a priority queue
     * */
    public MyPriorityQueue(){
        this(MAX_CAPACITY);
    }

    /**
     * Constructs a priority queue
     * @param capacity the priority queue maximum size
     * */
    public MyPriorityQueue(int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        currentSize = 0;
        array = new ArrayList<E>(capacity);
    }

    /**
     * Check whether this priority queue is empty
     * @return Return true if this priority queue contains no elements.
     * */
    public boolean isEmpty(){
        return 0 == currentSize;
    }

    /**
     * Check whether this priority queue is full
     * @return Return true if this priority queue contains maximum size
     * elements.
     * */
    public boolean isFull(){
        return capacity == currentSize;
    }

    /**
     * Remove all elements in this priority queue
     * */
    public void clear(){
        currentSize = 0;
    }
    
    /**
     * Insert an element into this priority queue
     * @param e element to be inserted to this priority queue
     * */
    public void insert(E e){
        if (isFull()){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (null == e){
            throw new NullPointerException();
        }
        array.add(e);
        int index = currentSize++;
        for (; index > 0; index = (index - 1)/2){
            E parent = array.get((index - 1)/2);
            if (e.compareTo(parent) > 0){
                array.set(index, parent);
            }
            else{
                break;
            }
        }
        array.set(index, e);
    }

    /**
     * Remove the largest element in this priority queue
     * @return the largest element
     * */
    public E remove(){
        if (isEmpty()){
            throw new RuntimeException("Empty Priority Queue");
        }
        E   min = array.get(ROOT_INDEX);
        E   temp = array.get(--currentSize);
        int index = ROOT_INDEX;
        for (int child = index; 2*(index + 1) <= currentSize; index = child){
            child = 2 * index + 1;
            if ((child + 1 != currentSize)
                    && (array.get(child).compareTo(array.get(child + 1)) < 0)){
                child++;
            }
            if (temp.compareTo(array.get(child)) < 0){
                array.set(index, array.get(child));
            }
            else{
                break;
            }
        }
        array.set(index, temp);
        array.remove(currentSize);

        return min;
    }

    /**
     * Get the largest element in this priority
     * @return the largest element
     * */
    public E head(){
        if (isEmpty()){
            throw new RuntimeException("Empty Priority Queue");
        }
        return array.get(ROOT_INDEX);
    }

    /**
     * Print this priority queue elements
     * */
    public void print(){
        for (E e : array){
            System.out.print(e + " ");
        }
        System.out.println();
    }

    /**
     * Compare this object with the specified object for order
     * @param other the object to be compared
     * @return a negative integer, zero, or a positive integer as this
     * object is less than, equal to, or greater than the specified
     * object.
     * */
    @Override
    public int compareTo(MyPriorityQueue<E> other){
        if (null == other){
            throw new NullPointerException();
        }
        return head().compareTo(other.head());
    }
}
