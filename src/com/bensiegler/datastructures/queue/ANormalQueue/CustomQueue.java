package com.bensiegler.datastructures.queue.ANormalQueue;

public class CustomQueue<T> {
    private Object[] array = new Object[1];
    private int size = 1, lastLocation = -1, writeLocation = 0;

    public void add(T data) {
        array[writeLocation] = data;
        writeLocation++;
        lastLocation++;
        sizingCheck();
    }

    public T peekFirst() throws EmptyQueueException {
        if(lastLocation < -1) {
            throw new EmptyQueueException("The queue is empty. Nothing to peek at");
        }else{
            return (T)array[0];
        }
    }

    public T peekLast() throws EmptyQueueException{
        try{
            return (T) array[lastLocation];
        }catch(ArrayIndexOutOfBoundsException e) {
            throw new EmptyQueueException("The queue is empty. Nothing to peek at");
        }
    }

    public T remove() throws EmptyQueueException {
        T storage;
        if((storage = (T)array[0]) != null) {
            lastLocation--;
            writeLocation--;
            for(int i = 0; i < array.length - 1; i++) {
                array[i] = array[i +1];
            }
            sizingCheck();
            return storage;
        }else{
            throw new EmptyQueueException("The queue is empty. Nothing to remove");
        }
    }

    private void sizingCheck() {
        int newSize = getCapacity();
        if(newSize != size) {
            size = newSize;

            Object[] newArray = new Object[size];

            for (int i = 0; i < newArray.length - 1; i++) {
                try {
                    newArray[i] = array[i];
                } catch (ArrayIndexOutOfBoundsException e) {
                    newArray[i] = null;
                }

            }
            array = newArray;
        }
    }

    private int getCapacity() {
        int counter = 0;
        for(int i = 0; i < size; i++) {
            if(array[i] != null) {
                counter++;
            }
        }
        double ratio = (double)counter / (double)size;

        if(ratio > 0.75) {
            return size * 2;
        }else if(ratio < 0.25) {
            return size / 2;
        }else{
            return size;
        }
    }

    public int getSize() {
        int counter = 0;
        for(int i = 0; i < size; i++) {
            if(array[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public void printAll() {
        for(Object obj: array) {
            if(obj != null) {
                System.out.println(obj);
            }
        }
    }
}