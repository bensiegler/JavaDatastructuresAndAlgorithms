package com.bensiegler.datastructures.tries.merkletrie;


public class Tester {

    public static void main(String[] args) throws Exception {
        MerkleTrie merkleTrie = new MerkleTrie();
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < 50000000; i++) {
            merkleTrie.add(i);
        }

        System.out.println("0 = " + merkleTrie.provePresent(0, 0));
        merkleTrie.remove(0);
        System.out.println("0 = " + merkleTrie.provePresent(0, 0));
        merkleTrie.add("0");
        System.out.println("0 = " + merkleTrie.provePresent(0, 0));
        System.out.println("8 = " + merkleTrie.provePresent(8, 7));

        System.out.println("total time = " + (System.currentTimeMillis()- timeStart));
    }
}

interface I {
    void method();
}

class A {
    public void method() {
        System.out.println("hi");
    }
}

class B extends A {
    public static void main(String[] args) {
        B b = new B();
        b.method();
        Float f = new Float("3.0");
        byte s = 5;
        System.out.println(s + f + 1);
    }
}
