package com.bensiegler.algorithms.sort;

import static java.lang.Thread.sleep;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {55, -4, 78, 32, 106, 79, 56, -12, 90, 43, 78564, 980,4, -89};

        insertionSort(array);

        for(int i: array) {
            System.out.print(i + ", ");
        }
    }

    public static void insertionSort(int[] arr) {
        int minValueIndex;
        for(int i = 0; i < arr.length; i++) {
            minValueIndex = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minValueIndex];
            arr[minValueIndex] = temp;
        }
    }
}
