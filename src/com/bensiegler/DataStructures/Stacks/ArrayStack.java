package com.bensiegler.DataStructures.Stacks;

//////////////////////////////
//DEFINITIVELY NOT THREAD SAFE
//////////////////////////////

public class ArrayStack {
    private static String Class;


    public static void main(String[] args)  {
        CustomStack<String> customStack = new CustomStack<String>(5);
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

class CustomStack<T> {
    private int readIndex = -1, insertIndex = 0;
    private Object[] stackArray = null;
    private int stackSize;

    CustomStack(int stackSize) {
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

    T pop() throws EmptyStackException{
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





