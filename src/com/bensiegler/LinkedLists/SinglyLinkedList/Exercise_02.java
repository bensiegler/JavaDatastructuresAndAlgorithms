package com.bensiegler.LinkedLists.SinglyLinkedList;
//IMPORTANT: THIS FAILS WHEN A LIST IS CREATED AND ALL ITEMS ARE REMOVED AND THEN AN ITEM IS ADDED AGAIN.
// TO FIX DEAL WITH NULL IN HEAD.
public class Exercise_02 {
    public static void main(String[] args) {
        CustomLinkedList<String> myList = new CustomLinkedList<>("should be middle");

        myList.addToFront("should be first");
        myList.addToEnd("should be end");

        int size = myList.getSize();
        for(int i = 0; i <= size; i++) {
            System.out.println(myList.pop());
        }


    }
}

class CustomLinkedList<T> {
    private Node<T> head = null;

    CustomLinkedList(T data) {
        head = new Node<>(data);
    }

    void addToEnd(T data) {
        if(head == null) {
            head = new Node<>(data);
        }else {
            Node iterator = head;

            while (iterator.next != null) {

                iterator = iterator.next;
            }
            iterator.next = new Node<>(data);
        }
    }

    void addToFront(T data) {
        Node<T> newHead = new Node<>(data);
        newHead.next = head;
        head = newHead;
    }

     T pop() {
       T data = head.getData();
       head = head.next;
       return data;
    }

     T get(T data) {
        Node<T> iterator = head;

        while(iterator.getData() != data) {
            iterator = iterator.next;
        }

        return iterator.getData();
    }

     T popLast() {
        Node<T> iterator = head,
                previous = null;

        while(iterator.next != null) {
            previous = iterator;
            iterator = iterator.next;
        }

        if(previous != null) {
            previous.next = null;
            return iterator.getData();
        }else {
            throw new NullPointerException();
        }

    }

     int getSize() {
        Node<T> iterator = head;
        int count = 0;

        while(iterator.next != null) {
            iterator = iterator.next;
            count++;
        }
        return count;
    }

    Node<T> getNode(int index) {
        Node<T> iterator = head;
        int count = 0;

        while(index != count) {
            iterator = iterator.next;
            count++;
        }

        return iterator;
    }

    Node<T> getHead() {
        return head;
    }

}


class Node<D> {
    private D data;
    Node<D> next = null;

    Node(D data) {
        this.data = data;
    }



     D getData() {
        return data;
    }

}
