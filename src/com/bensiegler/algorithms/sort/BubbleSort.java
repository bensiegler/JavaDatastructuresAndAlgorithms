package com.bensiegler.algorithms.sort;

import java.text.ParseException;

public class BubbleSort {
    public static void main(String[] args) throws ParseException {
        int arraySize = 100000;
        int[] arrayForBubble = new int[arraySize];
        int[] arrayForInsert = new int[arraySize];
        for(int i = 0; i < arrayForBubble.length; i++) {
            arrayForBubble[i] = (int) ((Math.random() * 10000) + 1);
            arrayForInsert[i] = (int) ((Math.random() * 10000) + 1);
        }
        //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        //Date timeBeforeBubble = formatter.parse(java.time.LocalTime.now().toString());
        long bubbleStart = System.currentTimeMillis();
        bubbleSort(arrayForBubble);
        long bubbleEnd = System.currentTimeMillis();

        System.out.println("Bubble sort took " + ((bubbleEnd - bubbleStart)));


        long insertionStart = System.currentTimeMillis();
        InsertionSort.insertionSort(arrayForInsert);
        long insertionEnd = System.currentTimeMillis();


        System.out.println("Insertion sort took " + (insertionEnd - insertionStart));

    }


    public static void bubbleSort(int[] arr) {
        for(int i = arr.length; i >= 0; i--) {
            for(int o = 0; o < i - 1; o++) {
                if(arr[o] > arr[o + 1]) {
                    int temp = arr[o];
                    arr[o] = arr[o + 1];
                    arr[o + 1] = temp;
                }
            }
        }
    }
}
