package com.bensiegler.DataStructures.Stacks.ArrayStack;

public class CustomArrayStack<T> {
    private int readIndex = -1, insertIndex = 0;
    private Object[] stackArray = null;
    private int stackSize;

    CustomArrayStack(int stackSize) {
        this.stackSize = stackSize;
        stackArray = new Object[stackSize];
    }

    int getSize() {
        return stackSize;
    }

    void push(T data) {
        stackArray[insertIndex] = data;
        readIndex = insertIndex;
        insertIndex++;

        if(checkCapacity() > 0.75) {
            stackSize = stackSize * 2;
            relocateData();
        }
    }

    T pop() throws EmptyStackException {
        if(stackArray[0] == null) {
            throw new EmptyStackException("the stack is empty. nothing to pop");
        }else {
            T storage = (T)stackArray[readIndex];
            readIndex--;
            insertIndex--;

            if (checkCapacity() < 0.3) {
                stackSize =(int) (stackSize / 1.5);
                relocateData();
            }
            return storage;
        }
    }

    private void relocateData() {
        Object[] newArray = new Object[stackSize];

        for(int i = 0; i < newArray.length - 1; i++) {
            try {
                newArray[i]= stackArray[i];
            }catch(ArrayIndexOutOfBoundsException e) {
                newArray[i] = null;
            }

        }

        stackArray = newArray;
    }

    private int checkCapacity() {
        int counter = 0;
        for(int i = 0; i < stackSize; i++) {
            if(stackArray[i] != null) {
                counter++;
            }
        }
        return counter/stackSize;
    }

    T peekFirst() {
        return (T)stackArray[readIndex];
    }

    T peekLast() {
        return (T)stackArray[0];
    }

    void printAll() {
        for (Object t : stackArray) {
            if(t != null) {
                System.out.println(t);
            }
        }
    }

}

