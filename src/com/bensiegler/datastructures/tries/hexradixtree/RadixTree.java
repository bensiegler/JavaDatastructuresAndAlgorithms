package com.bensiegler.datastructures.tries.hexradixtree;

public class RadixTree {

    private Node rootNode;

    public void add(String key, Object value) throws Exception {
        Node currentNode = retrieveNode(key);

        if(null == currentNode.getValue()) {
            //add value at this node
            currentNode.setValue(value);
        } else {
            //Node already has value. Did they mean edit?
            throw new Exception("That key already exists in the tree. Did you mean to use edit?");
        }
    }

    public Object search(String key) {
        return retrieveNode(key).getValue();
    }

    public void edit(String key, Object newValue) throws Exception {
        Node currentNode = retrieveNode(key);

        if(null == currentNode.getValue()) {
            throw new Exception("There is no value stored in this Node yet. Did you mean to use add?");
        }

        currentNode.setValue(newValue);
    }

    public void remove(String key) throws Exception {
        verifyKey(key);
        String hexKey = stringToHexString(key);

        Node currentNode;
        Node nextNode = rootNode;
        Node highestPossibleDelete = rootNode;
        String indexToDelete = "";
        boolean isSingleBranch = true;

        for(int i = 0; i < hexKey.length(); i++) {
            currentNode = nextNode;

            String indexOfNextNode = hexKey.substring(i, i + 1);
            try {
                nextNode = currentNode.traverseNodeWithoutCreation(indexOfNextNode);
            }catch (Exception e) {
                throw new Exception("No such data exists.");
            }

            if(!currentNode.isSingleItemNode() || !nextNode.isSingleItemNode()) {
                    isSingleBranch = false;
                    indexToDelete = indexOfNextNode;
                    highestPossibleDelete = nextNode;
            }
        }

        if(isSingleBranch) {
            rootNode.clear();
        }else {
            if (highestPossibleDelete.isSingleItemNode()) {
                highestPossibleDelete.getParent().deleteNodeAtIndex(indexToDelete);
            } else {
                highestPossibleDelete.setValue(null);
            }
        }
    }

    private Node retrieveNode(String key) {
        verifyKey(key);
        String hexKey = stringToHexString(key);

        Node currentNode = rootNode;
        for(int i = 0; i < hexKey.length(); i++) {
            currentNode = currentNode.traverseNodeWithCreation(hexKey.substring(i, i + 1));
        }

        return currentNode;
    }

    private char[] convertStringToCharArr(String str) {
        char[] keyChars = new char[str.length()];
        str.getChars(0, str.length(), keyChars, 0);
        return keyChars;
    }

    private String stringToHexString(String str) {
        char[] keyChars = convertStringToCharArr(str);
        StringBuilder builder = new StringBuilder();

        for (char keyChar : keyChars) {
            String charStr = Integer.toHexString(keyChar);
            builder.append(charStr);
        }

        return builder.toString();
    }

    private void verifyKey(String key) {
        if(key.contains(" ")) {
            throw new IllegalArgumentException("Keys cannot spaces!");
        }
    }

}
