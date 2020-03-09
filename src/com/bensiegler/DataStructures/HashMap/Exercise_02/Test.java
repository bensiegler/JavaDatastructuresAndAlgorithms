package com.bensiegler.DataStructures.HashMap.Exercise_02;

public class Test {
        public static void main(String[] args) {
            CustomHashMap<Integer, Integer> hashMap = new CustomHashMap<>();


//        hashMap.put(1, 2);
//        hashMap.put(11, 5);
//        hashMap.put(21, 4);
//        hashMap.put(1, 5);
//        hashMap.put(21, 99);
//        hashMap.put(29, 99);
//        hashMap.put(28, 99);
//        hashMap.put(24, 99);
//        hashMap.put(24, 99);
//        hashMap.put(25, 99);
//        hashMap.put(215, 99);
//        hashMap.put(217, 99);


//        hashMap.delete(217);
//        hashMap.delete(217);
//        System.out.println(hashMap.get(217));

            for(int i = 0; i < 200; i++) {
                hashMap.put(i, i + 1);
            }


            for(int i = 0; i < 200; i++) {
                hashMap.delete(i);
            }
//
//        for(int i = 0; i < 50; i++) {
//            hashMap.put(i, i + 1);
//        }
//
//        for(int i = 0; i < 200; i++) {
//            System.out.println(hashMap.get(i));
//        }
//
        }

    }


