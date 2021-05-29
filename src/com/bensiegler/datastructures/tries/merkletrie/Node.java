package com.bensiegler.datastructures.tries.merkletrie;

public abstract class Node {
    String hash;
    Node parent;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getHash() {
        return hash;
    }

    public final void nullifyHash() {
        hash = null;
    }

    public abstract String computeHash();

    public abstract Node testDataValidity(Node realNode, Node testNode) throws Exception;
}
