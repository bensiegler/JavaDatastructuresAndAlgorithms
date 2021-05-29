package com.bensiegler.datastructures.tries.hexradixtree;

import java.util.HashMap;

public class Tester {

        public static void main(String[] args) throws Exception {
            RadixTree tree = new RadixTree();
            HashMap<String, String> keysNValues = new HashMap<>();

            for(int i = 0; i < 10000; i++) {
                String key = generateRandomString();
                String value = generateRandomString();

                try {
                    tree.add(key, value);
                    keysNValues.put(key, value);
                }catch (Exception e) {
                    //do nothing
                }
            }

            String[] keys = keysNValues.keySet().toArray(new String[keysNValues.size()]);

            for(int i = 0; i < keys.length - 2;i++) {
                Object inTree = tree.search(keys[i]);
                Object inMap = keysNValues.get(keys[i]);
                if(!inTree.equals(inMap)) {
                    throw new Exception("mismatch between key n value");
                }

                tree.remove(keys[i]);
                keysNValues.remove(keys[i]);
            }

        }

    private static String generateRandomString() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < Math.random() * 3 + 1; i++) {
            char newChar = (char) (Math.random() * 26 + 'a');
            builder.append(newChar);
        }

        return builder.toString();
    }

}
