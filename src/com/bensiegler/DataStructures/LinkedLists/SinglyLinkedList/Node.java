package com.bensiegler.DataStructures.LinkedLists.SinglyLinkedList;

public class Node<D> {
    private D data;
    Node<D> next = null;

    Node(D data) {
        this.data = data;
    }

    D getData() {
        return data;
    }

}