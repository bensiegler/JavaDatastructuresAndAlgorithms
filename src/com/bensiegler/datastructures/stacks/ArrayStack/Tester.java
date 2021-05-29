package com.bensiegler.datastructures.stacks.ArrayStack;

//////////////////////////////
//DEFINITIVELY NOT THREAD SAFE
//////////////////////////////


public class Tester {
    private static String Class;


    public static void main(String[] args)  {
        CustomArrayStack<String> customStack = new CustomArrayStack<String>(5);
        customStack.push("hello");
        customStack.push("why");
        customStack.push("does my head hurt");

        try{
            System.out.println(customStack.pop());
            System.out.println(customStack.pop());
            customStack.push("HELLO");
            customStack.push("ANOTHER HI");
            System.out.println(customStack.peekFirst() + "peek first");
            System.out.println(customStack.peekLast() + "peek last");
            customStack.printAll();
        }catch(EmptyStackException e) {
            System.out.println(e.getMessage());
        }


    }
}






