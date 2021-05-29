package com.bensiegler.datastructures.stacks.LinkedListStackP;

class Tester {
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
