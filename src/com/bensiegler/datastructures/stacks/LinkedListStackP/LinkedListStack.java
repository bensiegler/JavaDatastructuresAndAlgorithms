package com.bensiegler.datastructures.stacks.LinkedListStackP;

import com.bensiegler.datastructures.stacks.ArrayStack.EmptyStackException;

public class LinkedListStack<T> {
    private Node<T> head;


    public void add(T data) {
       if(null == head) {
           head = new Node<>(data);
       }else {
           Node<T> nodeToAdd = new Node<>(data);
           nodeToAdd.setNext(head);
           head = nodeToAdd;
       }
    }


    public T pop() throws EmptyStackException {
        if(null == head) {
            throw new EmptyStackException("the stack is empty cannot pop");
        }

        Node<T> storage = head;
        head = head.getNext();
        return storage.getData();
    }

    public boolean contains(T data) {
        try {
            while(!head.getData().equals(data)) {
                head = head.getNext();
            }
            return true;
        }catch (NullPointerException e) {
            return false;
        }

    }

    public boolean isEmpty() {
        return null == head;
    }

    public T peek() throws EmptyStackException {
        if(head == null) {
            throw new EmptyStackException("The stack is empty. Nothing to peek at");
        }
        return head.getData();


    }
}



