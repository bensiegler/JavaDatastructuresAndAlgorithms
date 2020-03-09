package com.bensiegler.HashMap;

public class Exercise_02 {
    public static void main(String[] args) {
        CustomHashMap<Integer, Integer> hashMap = new CustomHashMap<>();


        hashMap.put(1, 2);
        hashMap.put(11, 5);
        hashMap.put(21, 4);
        hashMap.put(1, 5);
        hashMap.put(21, 99);
        hashMap.put(29, 99);
        hashMap.put(28, 99);
        hashMap.put(24, 99);
        hashMap.put(24, 99);
        hashMap.put(25, 99);
        hashMap.put(215, 99);
        hashMap.put(217, 99);


        System.out.println(hashMap.get(217));

//        for(int i = 0; i < 200; i++) {
//            hashMap.put(i, i + 1);
//        }
//
//
//        for(int i = 0; i < 200; i++) {
//            hashMap.delete(i);
//        }
//
//        for(int i = 0; i < 50; i++) {
//            hashMap.put(i, i + 1);
//        }
//
//        for(int i = 0; i < 200; i++) {
//            System.out.println(hashMap.get(i));
//        }
//
    }

}

class CustomHashMap<K, V> {
   private int numAdds = 0, size = 10;
   private Node[] nodeArray = new Node[10];
   private boolean imResizing;

    //what is supposed to happen if we add two identical keys? does it get the same hash?
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
        if(n.getValue().equals(key)) {
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

        if(null == n.next) {
            nodeArray[index] = null;
            numAdds--;
        }else{
            if(n.getKey().equals(key)) {
                nodeArray[index] = n.next;
                numAdds--;
                return;
            }

            try {
                while (n.next.getKey().equals(key)) {
                    n = n.next;
                }

                n.next = n.next.next;
                numAdds--;
            }catch(NullPointerException e) {
                if(!n.next.getValue().equals(key)) {
                    System.out.println("that item is not in the list");
                    return;
                }
                n.next = null;
                numAdds--;
            }

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

    private boolean resize() {
        if((numAdds / (double)nodeArray.length) > 0.7) {
            return true;
        }else return (numAdds / (double) nodeArray.length) < 0.3;
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

