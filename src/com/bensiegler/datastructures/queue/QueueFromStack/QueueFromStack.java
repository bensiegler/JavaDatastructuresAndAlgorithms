package com.bensiegler.datastructures.queue.QueueFromStack;

import com.bensiegler.datastructures.stacks.ArrayStack.EmptyStackException;
import com.bensiegler.datastructures.stacks.LinkedListStackP.LinkedListStack;

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


