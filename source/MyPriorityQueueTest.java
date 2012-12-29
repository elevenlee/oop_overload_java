/*
 * File Name:       MyPriorityQueueTest.java
 * Instructor:      Prof. Benjamin Goldberg
 *                  & Prof. Robert Grimm
 * Author:          Shen Li
 * UID:             N14361265
 * Department:      Computer Science
 * Note:            This file test MyPriorityQueue class
 * */

import java.util.ArrayList;
import java.util.Random;

/**
 * Test MyPriorityQueue
 * @author Shen Li
 * */
public class MyPriorityQueueTest{
    private static final int PQ_SIZE = 1000;
    private static final int LIST_SIZE = 15000;
    private static final int RANGE = 500000;

    /**
     * Sort an ArrayList
     * @param a the ArrayList to be sorted
     * @return Return the sorted ArrayList.
     * */
    public static <E extends Comparable<? super E>> ArrayList<E> sort(ArrayList<E> a){
        MyPriorityQueue<E> priorityQueue = new MyPriorityQueue<E>(a.size());
        ArrayList<E> sort = new ArrayList<E>(a.size());

        for (int i = 0; i < a.size(); i++){
            priorityQueue.insert(a.get(i));
        }
        for (int i = 0; !priorityQueue.isEmpty(); i++){
            sort.add(priorityQueue.remove());
        }
        return sort;
    }

    /**
     * Print the sorting result
     * @param a the sorted ArrayList
     * */
    public static <E extends Comparable<? super E>> void printResult(ArrayList<E> a){
        int index = 0;

        for (; index < a.size() - 1; index++){
            if (a.get(index).compareTo(a.get(index + 1)) < 0){
                break;
            }
        }
        if (index == a.size() - 1){
            System.out.println("Sorted");
        }
        else{
            System.out.println("Unsorted");
        }
    }

    /**
     * Test sorting a list of integers
     * */
    public static void testInt(){
        ArrayList<Integer> beforeSort = new ArrayList<Integer>(LIST_SIZE);
        ArrayList<Integer> afterSort;
        Random r = new Random();

        for (int i = 0; i < LIST_SIZE; i++){
            beforeSort.add(r.nextInt(RANGE));
        }
        afterSort = MyPriorityQueueTest.sort(beforeSort);
        MyPriorityQueueTest.printResult(afterSort);
    }

    /**
     * Test sorting a list of MyPriorityQueue object
     * */
    public static void testPQ(){
        ArrayList<MyPriorityQueue<Integer>> beforeSort = new ArrayList<MyPriorityQueue<Integer>>(PQ_SIZE);
        ArrayList<MyPriorityQueue<Integer>> afterSort;
        Random r = new Random();

        for (int i = 0; i < PQ_SIZE; i++){
            int capacity = r.nextInt(LIST_SIZE) + 1;
            MyPriorityQueue<Integer> p = new MyPriorityQueue<Integer>(capacity);
            for (int j = 0; j < capacity; j++){
                p.insert(r.nextInt(RANGE));
            }
            beforeSort.add(p);
        }
        afterSort = MyPriorityQueueTest.sort(beforeSort);
        MyPriorityQueueTest.printResult(afterSort);
    }

    /**
     * @param args the command line arguments
     * */
    public static void main(String[] args){
        MyPriorityQueueTest.testInt();
        MyPriorityQueueTest.testPQ();
    }
}
