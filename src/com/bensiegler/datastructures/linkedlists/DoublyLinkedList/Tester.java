package com.bensiegler.datastructures.linkedlists.DoublyLinkedList;

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
