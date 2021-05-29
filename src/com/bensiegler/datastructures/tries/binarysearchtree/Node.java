package com.bensiegler.datastructures.tries.binarysearchtree;

public class Node<V> {
    private V value;
    private Integer key;
    private Node<V> leftChild;
    private Node<V> rightChild;

    public Node<V> getParent() {
        return parent;
    }

    public void setParent(Node<V> parent) {
        this.parent = parent;
    }

    private Node<V> parent;

    public Node(Integer key, V value) {
        this.value = value;
        this.key = key;
    }

    public Node<V> getLeftChild() {
        return leftChild;
    }

    public Node<V> getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node<V> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<V> rightChild) {
        this.rightChild = rightChild;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
