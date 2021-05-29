package com.bensiegler.datastructures.tries.merkletrie;

public class Leaf extends Node {
    private Object data;

    public Leaf(Object data) {
        this.data = data;
    }

    public Leaf(Object data, Branch parent) {
        this.parent = parent;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String computeHash() {
        hash = MerkleTrie.getSha256Hash(data.toString());
        return hash;
    }

    public Node testDataValidity(Node realNode, Node testNode) throws Exception {
        if(!testNode.getClass().equals(Leaf.class)) {
            throw new ClassCastException("Cannot cast a branch to a leaf!");
        }

        Leaf testLeaf = (Leaf) testNode;

        if(!MerkleTrie.getSha256Hash(testLeaf.getData().toString()).equals(hash)) {
            throw new Exception("Hash mismatch!");
        }

        return null;
    }

}
