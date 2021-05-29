package com.bensiegler.datastructures.queue.ANormalQueue;

public class Tester {
    public static void main(String[] args) {
        CustomQueue<String> queue = new CustomQueue<>();
        queue.add("1st");
        queue.add("2nd");
        queue.add("3rd");
        queue.add("4th");
        System.out.println(queue.getSize() + " size");
        queue.printAll();

        try {
            System.out.println(queue.remove());
            System.out.println(queue.peekFirst() + " first");
            System.out.println(queue.peekLast() + " last");



        }catch(EmptyQueueException e) {
            System.out.println(e.getMessage());
        }
    }
}




