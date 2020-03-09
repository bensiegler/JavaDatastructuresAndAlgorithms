package com.bensiegler.Queue;

import com.bensiegler.Stacks.EmptyStackException;
import com.bensiegler.Stacks.LinkedListStack;

public class QueueFromStack<T> {
    LinkedListStack<T> stack = new LinkedListStack<>();

    //add
    public void push(T data) {
        stack.add(data);
    }

    //remove
    public T pop() throws EmptyStackException {
        LinkedListStack<T> storage = new LinkedListStack<>();

        while(!stack.isEmpty()) {
            storage.add(stack.pop());
        }

        T dataToReturn = storage.pop();

        while(!storage.isEmpty()) {
            stack.add(storage.pop());
        }

        return dataToReturn;
    }

    //contains
    public boolean contains(T data) {
        return stack.contains(data);
    }
    //peek
    public T peek() throws EmptyStackException {
        LinkedListStack<T> storage = new LinkedListStack<>();

        while(!stack.isEmpty()) {
            storage.add(stack.pop());
        }

        return storage.peek();
    }
}

class test {
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
