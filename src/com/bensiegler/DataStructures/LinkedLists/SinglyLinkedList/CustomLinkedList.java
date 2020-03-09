package com.bensiegler.DataStructures.LinkedLists.SinglyLinkedList;

public class CustomLinkedList<T> {
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
