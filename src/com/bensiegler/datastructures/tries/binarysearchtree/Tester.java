package com.bensiegler.datastructures.tries.binarysearchtree;

import com.bensiegler.datastructures.tries.binarysearchtree.exceptions.AlreadyInTreeException;
import com.bensiegler.datastructures.tries.binarysearchtree.exceptions.EmptyTreeException;

public class Tester {
    public static void main(String[] args) {
        CustomBinarySearchTree<String> tree = new CustomBinarySearchTree<>();

        try {
            tree.insert(5, "Hello");
            tree.insert(2, "2 here");
            tree.insert(4, "Hi!");
            tree.insert(6, "Bye!");
            tree.insert(43, "43 here");
            tree.insert(1, "1 here");
            tree.insert(74, "74 here");
            tree.insert(45, "45 here");
            tree.insert(490, "490 here");
            tree.insert(3, "3 here");


            System.out.println(tree.search(3));
            tree.remove(3);
            System.out.println(tree.search(3));

        }catch(AlreadyInTreeException | EmptyTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
