package com.bensiegler.DataStructures.Queue.QueueFromStack;

import com.bensiegler.DataStructures.Stacks.ArrayStack.EmptyStackException;

public class Tester {
    public static void main(String[] args) {
        QueueFromStack<String> queueFromStack = new QueueFromStack<>();

        queueFromStack.push("Hello");
        queueFromStack.push("How");
        queueFromStack.push("Are");
        queueFromStack.push("You");
        queueFromStack.push("Today?");

        try{
            System.out.println(queueFromStack.pop());
            System.out.println(queueFromStack.pop());
            System.out.println(queueFromStack.pop());
            System.out.println(queueFromStack.pop());
            System.out.println(queueFromStack.pop());
            System.out.println(queueFromStack.pop());

        }catch(EmptyStackException e) {
            System.out.println(e.getMessage());
        }
    }
}