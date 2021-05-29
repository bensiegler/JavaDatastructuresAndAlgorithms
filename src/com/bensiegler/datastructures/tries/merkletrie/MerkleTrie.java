package com.bensiegler.datastructures.tries.merkletrie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class MerkleTrie {
    private Branch rootNode;
    private int idCounter = 0;
    private ArrayList<HashMap<Integer, Node>> levels = new ArrayList<>();
    private final ArrayList<Integer> vacantIndices = new ArrayList<>();

    public MerkleTrie() {
        levels.add(new HashMap<>());
    }

    public int add(Object object) {
        if(vacantIndices.isEmpty()) {
            levels.get(0).put(idCounter, new Leaf(object));
            idCounter++;
            createTrieStructure();
            return idCounter;
        }else{
            int vacantIndex = vacantIndices.get(0);
            levels.get(0).put(vacantIndex, new Leaf(object));
            vacantIndices.remove(0);
            createTrieStructure();
            return vacantIndex;
        }
    }

    public void remove(int indexOfLeafToRemove) {
        createTrieStructure();
        HashMap<Integer, Node> leaves = levels.get(0);

        if(leaves.get(indexOfLeafToRemove) == null) {
            throw new NoSuchElementException("No data found at that index.");
        }

        Leaf removedLeaf = (Leaf) leaves.remove(indexOfLeafToRemove);
        vacantIndices.add(indexOfLeafToRemove);
        nullifyHashes(removedLeaf);
    }

    public void edit(int indexOfEdit, Object newData) {
        createTrieStructure();
        HashMap<Integer, Node> leaves = levels.get(0);
        Leaf leaf;
        if((leaf = (Leaf)leaves.get(indexOfEdit)) == null) {
            throw new NoSuchElementException("No data found at that index.");
        }

        leaf.setData(newData);
        nullifyHashes(leaf);
    }

    public boolean provePresent(int expectedIndex, Object expectedData) {
        getRootHash();
        HashMap<Integer, Node> leaves = levels.get(0);
        Node realNode = leaves.get(expectedIndex);

        if(realNode == null) {
            return false;
        }

        Node testNode = new Leaf(expectedData);
        Node parent = realNode.getParent();

        while(parent != null) {
            try {
                testNode = parent.testDataValidity(realNode, testNode);
            }catch (Exception e) {
                return false;
            }

            realNode = parent;
            parent = parent.getParent();

        }

        return true;
    }

    private void nullifyHashes(Leaf leaf) {
        leaf.nullifyHash();
        //set upstream hashes to null.
        Node parent = leaf;
        while(parent != null) {
            parent.nullifyHash();
            parent = parent.getParent();
        }

    }

    public String getRootHash() {
        createTrieStructure();

        if(null == rootNode.getHash()) {
            return rootNode.computeHash();
        }else{
            return rootNode.getHash();
        }
    }

    private void createTrieStructure() {
        //clear previous structure
        HashMap<Integer, Node> currentLevel = levels.get(0);
        levels = new ArrayList<>();
        levels.add(currentLevel);

        for(int i = 0; i < getTrieDepth() - 1; i++) {
            currentLevel = levels.get(i);
            HashMap<Integer, Node> oneLevelUp = new HashMap<>();
            levels.add(oneLevelUp);

            for(int j = 0; j < currentLevel.size(); j+= 2) {
                Node leftChild = currentLevel.get(j);
                Node rightChild = currentLevel.get(j + 1);
                Branch newBranch = new Branch(leftChild, rightChild);
                oneLevelUp.put(j/2, newBranch);

                try {
                    leftChild.setParent(newBranch);
                }catch (NullPointerException e) {
                    //do nothing
                }
                try {
                    rightChild.setParent(newBranch);
                }catch (NullPointerException e) {
                    //do nothing
                }
            }
        }

        rootNode = (Branch) levels.get(levels.size() - 1).get(0);
    }

    private int getTrieDepth() {
        int numNodes = levels.get(0).size();
        int counter = 1;

        while(numNodes != 1) {
            counter++;
            if(numNodes % 2 != 0) {
                numNodes += 1;
            }

            numNodes /= 2;
        }

        if(counter == 1){
            return 2;
        }

        return counter;
    }

    public static String getSha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(digest.digest(input.getBytes()));
        }catch (NoSuchAlgorithmException e) {
            //won't happen
            return null;
        }
    }
}
