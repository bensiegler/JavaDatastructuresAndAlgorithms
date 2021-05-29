package com.bensiegler.datastructures.tries.hexradixtree;

import java.util.Arrays;

public class Node {

    private final Object[] data = new Object[17];
    private Node parent;

    public Node(Node parent) {
        this.parent = parent;
    }

    public Node traverseNodeWithCreation(String hexChar) {
        int index = getIndexFromHex(hexChar);
        Node possibleNextNode = (Node) data[index];

        if(null == possibleNextNode) {
            //new node required
            Node newNode = new Node(this);
            data[index] = newNode;
            return newNode;
        }else{
            //node exists return
            return possibleNextNode;
        }
    }

    public Node traverseNodeWithoutCreation(String hexChar) throws Exception {
        int index = getIndexFromHex(hexChar);
        Node possibleNextNode = (Node) data[index];

        if(null == possibleNextNode) {
            throw new Exception("Invalid Path. No data stored in index " + index);
        }else{
            //node exists return
            return possibleNextNode;
        }
    }

    public Node getParent() {
        return parent;
    }

    public Object getValue() {
        return data[16];
    }

    public boolean isSingleItemNode() {
        int itemCount = 0;
        for(int i = 0; i < data.length; i++) {
            if(null != data[i]) {
                itemCount++;
            }

            if(itemCount > 1) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        Arrays.fill(data, null);
    }

    public void setValue(Object value) {
        data[16] = value;
    }

    public void deleteNodeAtIndex(String hexChar) {
        data[getIndexFromHex(hexChar)] = null;
    }

    private int getIndexFromHex(String hexChar) {
        try {
            return Integer.parseInt(hexChar);
        }catch (NumberFormatException e) {
            switch (hexChar.toLowerCase()) {
                case "a": return 10;
                case "b": return 11;
                case "c": return 12;
                case "d": return 13;
                case "e": return 14;
                case "f": return 15;
                default: throw new IllegalArgumentException("Did not pass in a valid hex character!");
            }
        }
    }
}
