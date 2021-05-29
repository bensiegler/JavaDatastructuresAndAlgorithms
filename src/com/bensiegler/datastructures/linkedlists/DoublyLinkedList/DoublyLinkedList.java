package com.bensiegler.datastructures.linkedlists.DoublyLinkedList;

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










