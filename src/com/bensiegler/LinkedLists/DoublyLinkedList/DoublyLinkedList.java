package com.bensiegler.LinkedLists.DoublyLinkedList;

public class DoublyLinkedList<T> {

    private Node<T> head = null;


    public void add(T data) {
        Node<T> newNode;
        if (head == null) {
            newNode = new Node<>(data);
        } else {
            newNode = new Node<>(data, head);
            head.previous = newNode;
        }
        head = newNode;
    }

    public T peek() {
        return head.getData();
    }

    public T get(T data) throws NotInListException {
        if(head.getData() == data) {
            return head.getData();
        }else {
            Node iterator = head;
            while (data != iterator.getData()) {
                if (null == iterator.next) {
                    throw new NotInListException("That item is not in the list");
                }
                iterator = iterator.next;
            }
            return (T)iterator.getData();
        }
    }

    public void remove(T data) throws NotInListException, EmptyListException {
        Node<T> cursor = head;
        if (head != null) {
            if (cursor.getData() == data) {
                if (null == cursor.next) {
                    head = null;
                } else {
                    head = cursor.next;
                    head.previous = null;
                }

            } else {
                while (cursor.getData() != data) {
                    if (null == cursor.next) {
                        throw new NotInListException("That item is not in the list");
                    } else {
                        cursor = cursor.next;
                    }
                }

                T storage = cursor.getData();

                if (null == cursor.next) {
                    cursor.previous.next = null;
                } else {
                    cursor.next.previous = cursor.previous;
                    cursor.previous.next = cursor.next;
                }
            }

        }else{
            throw new EmptyListException("The list is empty. Nothing to remove.");
        }


    }

    public void printAll() {
        Node<T> cursor = head;
        while (cursor != null) {
            System.out.println(cursor.getData());
            cursor = cursor.next;
        }

    }
}

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


class Tester {
    public static void main(String[] args) {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();

        linkedList.add("Ben");
        linkedList.add("Jade");
        linkedList.add("Isadora");


        //System.out.println(linkedList.peek());
        //linkedList.printAll();

        try{
            linkedList.remove("Isadora");
            linkedList.remove("Ben");
            linkedList.remove("Jade");
            linkedList.remove("hi");
            linkedList.add("Joe");
            linkedList.printAll();
        }catch(EmptyListException e) {
            System.out.println(e.getMessage());
        }catch(NotInListException e) {
            System.out.println(e.getMessage());
        }







    }
}

class EmptyListException extends Exception {
    EmptyListException(String message) {
        super(message);
    }
}

class NotInListException extends Exception {
    NotInListException(String message) {
        super(message);
    }
}

