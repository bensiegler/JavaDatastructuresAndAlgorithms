package com.bensiegler.datastructures.tries.merkletrie;

public class Tester {

    public static void main(String[] args) throws Exception {
        MerkleTrie merkleTrie = new MerkleTrie();

        merkleTrie.add("0");
        merkleTrie.add("1");
        merkleTrie.add("2");
        merkleTrie.add("3");
        merkleTrie.add("4");
        merkleTrie.add("5");
        merkleTrie.add("6");
        merkleTrie.add("7");
        merkleTrie.add("8");
        merkleTrie.add("9");

        System.out.println("0 = " + merkleTrie.provePresent(0, "0"));
        merkleTrie.remove(0);
        System.out.println("0 = " + merkleTrie.provePresent(0, "0"));
        merkleTrie.add("0");
        System.out.println("0 = " + merkleTrie.provePresent(0, "0")); 
        System.out.println("8 = " + merkleTrie.provePresent(8, "8"));


    }
}
