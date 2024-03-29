package com.bensiegler.datastructures.hashmap;

class CustomHashMap<K, V> {
    private int numAdds = 0, size = 10;
    private Node[] nodeArray = new Node[10];
    private boolean imResizing;

    public void put(K key, V value) {
        int index = hash(key);
        numAdds++;

        if(get(key) != null) {
            Node<K, V> iterator = (Node<K,V>) nodeArray[index];
            if(iterator.getKey().equals(key)) {
                iterator.setValue(value);
            }else {
                while (!iterator.getKey().equals(key)) {
                    iterator = iterator.next;
                }
                iterator.setValue(value);
            }
            return;
        }
        if (nodeArray[index] == null) {
            nodeArray[index] = new Node<>(key, value);
        } else {
            Node<K, V> iterator = (Node<K, V>) nodeArray[index];
            while (null != iterator.next) {
                iterator= iterator.next;
            }
            iterator.next = new Node<>(key, value);

        }
        if(!imResizing) {
            checkCapacity();
        }


    }

    public V get(K key) {
        int index = hash(key);

        if(nodeArray[index] == null) {
            return null;
        }

        Node<K, V> n = (Node<K,V>) nodeArray[index];
        if(n.getKey().equals(key)) {
            return n.getValue();
        }else{
            try {
                while(!n.getKey().equals(key)) {
                    n = n.next;
                }
                return n.getValue();
            }catch(NullPointerException e) {
                return null;
            }

        }
    }

    public void delete(K key) {
        int index = key.hashCode() % nodeArray.length;
        checkCapacity();

        if(nodeArray[index] == null) {
            System.out.println("that item is not in the list");
            return;
        }

        Node<K, V> n = (Node<K, V>) nodeArray[index];

        if(n.getKey().equals(key)) {
            if(n.next == null) {
                nodeArray[index] = null;
            }else{
                nodeArray[index] = n.next;
            }
            numAdds--;
        }else{
            try {
                while (n.next.getKey().equals(key)) {
                    n = n.next;
                }
            }catch(NullPointerException e) {
                System.out.println("that item is not in the list");
                return;
            }

            if(null == n.next.next) {
                n.next = null;
            }else {
                n.next = n.next.next;
            }
            numAdds--;
        }
        if(!imResizing) {
            checkCapacity();
        }
    }

    public boolean containsKey(K key) {
        if(get(key) == null) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmpty() {
        for(Node<K, V> n: nodeArray) {
            try{
                n.getValue();
                return false;
            }catch(NullPointerException e) {
                //do nothing... iterate through;
            }
        }
        return true;
    }

    private int hash(K key) {
        return key.hashCode() % size;
    }

    private void checkCapacity() {
        if ((numAdds / (double) nodeArray.length) > 0.7) {
            size *= 2;
            System.out.println("NOW GROWING TO SIZE " + size);
            transferToNewArray(size);
        }else if((numAdds / (double) nodeArray.length) < 0.3) {
            size /= 2;
            System.out.println("NOW SHRINKING TO SIZE " + size);
            transferToNewArray(size);
        }
    }

    private void transferToNewArray(int length) {
        imResizing = true;
        Node[] oldArray = nodeArray;
        nodeArray = new Node[length];
        numAdds = 0;

        for(int i = 0; i < oldArray.length; i++) {
            Node<K,V> currentNode = (Node<K,V>) oldArray[i];

            try {
                put(currentNode.getKey(),currentNode.getValue());

                while(currentNode.next != null) {
                    currentNode = currentNode.next;
                    put(currentNode.getKey(),currentNode.getValue());

                }
            }catch(NullPointerException e) {
                //dont do anything move onto next index
            }
        }
        imResizing = false;
    }

}
