package com.bensiegler.DataStructures.HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class Exercise_01 {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("VI", "Virgin Islands");
        hashMap.put("NY", "New York");
        hashMap.put("CA", "California");
        hashMap.put("VT", "Vermont");
        hashMap.put("NH", "New Hampshire");
        hashMap.put("NJ", "New Jersey");

        System.out.println(hashMap.get("NY"));

        HashMap<String, String> anotherHashMap = new HashMap<>();
        anotherHashMap.put("AZ", "Arizona");

        hashMap.putAll(anotherHashMap);

        System.out.println(hashMap.size());

        System.out.println("the hashmap contains VI = " + (hashMap.containsKey("VI") && hashMap.containsValue("Virgin Islands")));

       Set<String> set = hashMap.keySet();

       for(String s: set) {
           System.out.println(s);
       }

       Set<Map.Entry<String, String>> anotherSet = hashMap.entrySet();

       hashMap.putIfAbsent("VI", "Virgin Islands");
       hashMap.putIfAbsent("CO" , "Colorado");

       set = hashMap.keySet();
       for(String s: set) {
           System.out.println(s);
       }

       hashMap.remove("VI");

       hashMap.replace("AZ", "Oregon");

       System.out.println(hashMap.get("AZ"));

        BiConsumer<String, String> lambda = (String s1, String s2) -> {
            System.out.println(s1 + " " + s2 + " in biConsumer");
        };

       hashMap.forEach(lambda);

        hashMap.clear();
    }
}
