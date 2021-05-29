package com.bensiegler.datastructures.linkedlists.SinglyLinkedList;
//IMPORTANT: THIS FAILS WHEN A LIST IS CREATED AND ALL ITEMS ARE REMOVED AND THEN AN ITEM IS ADDED AGAIN.
// TO FIX DEAL WITH NULL IN HEAD.
public class Tester {
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





