package com.bensiegler.Stacks;

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


    public T pop() throws EmptyStackException{
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

class Node<T> {
    private T data;
    private Node<T> next;

    Node(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

class test {
    public static void main(String[] args) {
        LinkedListStack<String> linkedListStack = new LinkedListStack<>();

        linkedListStack.add("Hello");
        linkedListStack.add("Hey");
        linkedListStack.add("Goodday");

        try {
            System.out.println(linkedListStack.pop());
            System.out.println(linkedListStack.pop());
            System.out.println(linkedListStack.isEmpty());


        }catch(Exception e) {
            System.out.println(e.getMessage());
        }


    }
}