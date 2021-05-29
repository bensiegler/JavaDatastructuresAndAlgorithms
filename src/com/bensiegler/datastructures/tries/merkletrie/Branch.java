package com.bensiegler.datastructures.tries.merkletrie;

public class Branch extends Node {
    private Node leftChild;
    private Node rightChild;

    public Branch() {
    }

    public Branch(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node testDataValidity(Node realNode, Node testNode) throws Exception {
        String testHash = "";
        StringBuilder hashBuilder = new StringBuilder();
        Node testParent;
        if(realNode.equals(leftChild)) {
            hashBuilder.append(testNode.computeHash() + "|");
            try {
                hashBuilder.append(rightChild.computeHash());
            }catch (NullPointerException e) {
                //do nothing
            }
            testHash = MerkleTrie.getSha256Hash(hashBuilder.toString());
            testParent = new Branch(testNode, rightChild);
        }else if(realNode.equals(rightChild)) {
            try {
                hashBuilder.append(leftChild.computeHash());
            }catch (NullPointerException e) {
                //do nothing
            }
            hashBuilder.append("|" + realNode.computeHash());

            testHash = MerkleTrie.getSha256Hash(hashBuilder.toString());
            testParent = new Branch(leftChild, testNode);
        }else{
            throw new Exception("realNode(child) did not match either node in the parent");
        }

        if(!testHash.equals(hash)) {
            throw new Exception("Hash mismatch!");
        }

        testParent.computeHash();
        return testParent;
    }

    public String computeHash() {
        String leftChildHash;
        String rightChildHash;

        try {
            if((leftChildHash = leftChild.getHash()) == null) {
                leftChildHash = leftChild.computeHash();
            }
        }catch (NullPointerException e) {
            leftChildHash = "";
        }
        try {
            if((rightChildHash = rightChild.getHash()) == null) {
                rightChildHash = rightChild.computeHash();
            }
        }catch (NullPointerException e) {
            rightChildHash = "";
        }

        hash = MerkleTrie.getSha256Hash(leftChildHash + "|" + rightChildHash);
        return hash;
    }

}
