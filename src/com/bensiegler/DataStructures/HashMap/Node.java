package com.bensiegler.DataStructures.HashMap;

class Node<K, V> {
    private K key;
    private V value;
    Node<K, V> next;

    public void setValue(V value) {
        this.value = value;
    }

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}


