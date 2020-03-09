package com.bensiegler.DataStructures.LinkedLists.DoublyLinkedList;

class Node<T> {
    private T data;
    protected Node<T> next, previous;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

}
