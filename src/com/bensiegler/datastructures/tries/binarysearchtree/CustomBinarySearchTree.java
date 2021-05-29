package com.bensiegler.datastructures.tries.binarysearchtree;

import com.bensiegler.datastructures.tries.binarysearchtree.exceptions.AlreadyInTreeException;
import com.bensiegler.datastructures.tries.binarysearchtree.exceptions.EmptyTreeException;

public class CustomBinarySearchTree<V> {
    private Node<V> root;
    private int numAdds;

    public void insert(Integer key, V value) throws AlreadyInTreeException {
        if (null == root) {
            root = new Node<V>(key, value);
            numAdds++;
            return;
        }

        Node<V> iterator = root, parent = root;
        int count = 0;
        String previousMove = "";
        while (true) {
            try {
                if (iterator.getKey() < key) {
                    if (iterator.getRightChild() == null) {
                        throw new NullPointerException();
                    }
                    previousMove = "right";
                    iterator = iterator.getRightChild();
                } else if (iterator.getKey() > key) {
                    if (iterator.getLeftChild() == null) {
                        throw new NullPointerException();
                    }
                    previousMove = "left";
                    iterator = iterator.getLeftChild();
                } else {
                    throw new AlreadyInTreeException("That key is already in the tree");
                }

                if(count != 0) {
                    if (previousMove.equals("right")) {
                        parent = parent.getRightChild();
                    } else {
                        parent = parent.getLeftChild();
                    }
                }

            } catch (NullPointerException e) {
                if(count != 0) {
                    if (previousMove.equals("right")) {
                        parent = parent.getRightChild();
                    } else {
                        parent = parent.getLeftChild();
                    }
                }
                break;
            }


            count++;
        }

        if (iterator.getKey() > key) {
            parent.setLeftChild(new Node<>(key, value));
            parent.getLeftChild().setParent(parent);
        } else if (iterator.getKey() < key) {
            parent.setRightChild(new Node<>(key, value));
            parent.getRightChild().setParent(parent);
        }
        numAdds++;

    }


    /*
      SEARCH METHOD (V)
        1. check if the root of the tree is null in which case there is no data.
        2. start at the root and check is the data in the node greater than what was passed
            --> check next node left
           equal to
               --> you found the right node. return it.
           less than
               --> check node right
    */
    public V search(Integer key) {
        if (null == root) {
            return null;
        }
        Node<V> iterator = root;
        while (true) {
            try {
                if (iterator.getKey().equals(key)) {
                    return iterator.getValue();
                } else if (iterator.getKey() > key) {
                    iterator = iterator.getLeftChild();
                } else {
                    iterator = iterator.getRightChild();
                }
            } catch (NullPointerException e) {
                return null;
            }
        }


    }

    /*

       REMOVE METHOD (VOID)
       1. check if root is null. if true tree == empty
       2. start traversing the tree to find the value. same logic as search method
       3. once the right node is found store the values of the whole subtree below it in a new array of nodes.
       4. from the node to be removed start adding the values back in.

     */


    public void remove(Integer key) throws EmptyTreeException {
        if (null == root) {
            throw new EmptyTreeException("The tree is empty. Nothing to remove");
        }
        String previousMove = "";
        Node<V> iterator = root;
        while (true) {
            try {
                if (iterator.getKey() > key) {
                    iterator = iterator.getLeftChild();
                    previousMove = "left";
                } else if (iterator.getKey() < key) {
                    iterator = iterator.getRightChild();
                    previousMove = "right";
                } else {
                    break;
                }


            } catch (NullPointerException e) {
                break;
            }
        }

        if(iterator.getRightChild() == null && iterator.getLeftChild() == null) {
            if(previousMove.equals("right")) {
                iterator.getParent().setRightChild(null);
            }else{
                iterator.getParent().setLeftChild(null);
            }
        }else if(iterator.getRightChild() == null) {
            if(previousMove.equals("right")) {
                iterator.setRightChild(iterator.getLeftChild());
            }else{
                iterator.setLeftChild(iterator.getLeftChild());
            }
        }else if(iterator.getLeftChild() == null) {
            if(previousMove.equals("right")) {
                iterator.setRightChild(iterator.getRightChild());
            }else{
                iterator.setLeftChild(iterator.getRightChild());
            }
        }else{
            Node<V> leftChild = iterator.getLeftChild(),
                    rightChild = iterator.getRightChild();

            if(previousMove.equals("right")) {
                iterator.getParent().setRightChild(null);
            }else{
                iterator.getParent().setLeftChild(null);
            }

            putLeftBack(leftChild);
            putRightBack(rightChild);
        }
    }

    private void putLeftBack(Node<V> n) {
        try {
            insert(n.getKey(), n.getValue());
        }catch(AlreadyInTreeException e) {
            //will not happen but have to catch it to use method.
        }

        if(n.getLeftChild() != null) {
            putLeftBack(n.getLeftChild());
        }
        if(n.getRightChild() != null) {
            putRightBack(n.getRightChild());
        }
    }

    private void putRightBack(Node<V> n) {
        try{
            insert(n.getKey(), n.getValue());
        }catch(AlreadyInTreeException e) {
            //will not happen but have to catch it to use method.
        }

        if(n.getRightChild() != null) {
            putRightBack(n.getRightChild());
        }

        if(n.getLeftChild() != null) {
            putLeftBack(n.getLeftChild());
        }
    }

}