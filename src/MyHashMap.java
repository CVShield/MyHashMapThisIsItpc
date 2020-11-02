package com.company;
import java.util.HashMap;
public class MyHashMap {
    Node[] buckets;

    public MyHashMap() {
            buckets = new Node[16];
            for(int x = 0; x < 16; x++){
                Node node = new Node(null, null);
                buckets[x] = node;
            }
        }
        //empty not utilized atm

        public void empty () { //Empties out the existing HashMap
            for(int x = 0; x < buckets.length; x++) {
                buckets[x] = null;
            }
            }

//not empty
        public boolean isEmpty(){//Returns true if all indexes are null
            for(int x = 0; x < buckets.length; x++){
                if(buckets[x] == null) {
                }else {
                    return false;
                }
            }
            return true;
        }

        public void put (Object key, Object value) {

            Node node = new Node(value, key);
            int index = calculateIndex(key);
            Node current = buckets[index];
            if (buckets[index].getKey() == null) {
                buckets[index] = node;
            }
            else if (containsKey(key)) {
                System.out.println("Key in use");
            } else {

                    while (current.getNext() != null) {
                        current = current.getNext();
                    }
                current.setNext(node);
            }
        }


    public void putIfAbsent (Object key, Object value)//It inserts the specified value with the specified key in the map only if it is not already specified.
    // HashMap.putIfAbsent(key, value);
        {
            boolean isKey = containsKey(key);
            boolean isValue = containsValue(value);
            if(isKey && isValue) {
                System.out.println("Unavailable");
            } else{
            System.out.println("Available");
                put(key, value);
            }
            }




//removes objects
        public Object remove (Object key, Object value){ //Returns the key/value of item being removed
            Node current = buckets[calculateIndex(key)];
            Object temp = null;
            if(current.getData()==value && current.getKey()==key){
                temp = current.getData();
                buckets[calculateIndex(key)] = current.getNext();
            }
            while (current.getNext() != null) {
                if (current.getNext().getData() == value) {
                    temp = current.getNext().getData();
                    if (current.getNext().getNext() == null) {
                        current.clearNext();
                    } else {
                        current.setNext(current.getNext().getNext());
                    }
                }else {
                    current = current.getNext();
                }
            }
            return temp;
        }

        public boolean containsValue (Object value){//
            for(int x = 0; x < buckets.length; x++){//ArrayIndexOutOfBoundsException
                Node current = buckets[x];
                if(current.getData() == value)
                    return true;
                while(current.getNext()!=null) {
                    if (current.getData().equals(value))
                        return true;
                    current = current.getNext();
                }
                }
            return false;
        }

        public boolean containsKey (Object key) {
            Node current = buckets[calculateIndex(key)];
            if(current.getKey().equals(key))
                    return true;
            while (current.getNext()!=null){
                if(current.getKey().equals(key))
                    return true;
                current = current.getNext();
            }
            return false;
        }

        public Object get (Object key) {
        //returns node val
            Node current = buckets[calculateIndex(key)];
            if(current.getKey() == key)
                return current.getData();
            while (current.getNext() != null) {
                if (current.getKey() == key) {
                    return current.getData();
                }
            }
            return null;
        }

        public Object getOrDefault (Object key, Object value)
        { //It returns the value to which the specified key is mapped, or defaultValue if the map contains no mapping for the key.
            Object defalt = value;
            Node current = buckets[calculateIndex(key)];
            if(current.getKey()==key)
                return current.getData();
            while(current.getNext()!=null){//fine
                if(current.getKey()==key)
                    defalt = current.getData();
            }
            return defalt;
        }
//works :)
        public int size () { //Number of Nodes in the Linked List

        int count = 0;
            for(int x = 0; x < buckets.length; x++){//fix loop(null point)
                Node current = buckets[x];//must return in int

                if(current.getData() != null)
                    count++;
                while(current.getNext()!=null){
                    count++;
                    current = current.getNext();
                }
            }
            return count;
        }

        public Object replace (Object key, Object value) { //Return old value being replaced. Returns null if key does not exist already.
            Object temp = null;
            Node current = buckets[calculateIndex(key)];
            if(buckets[calculateIndex(key)].getKey() == key){

                temp = current.getData();//
                current.setData(value);
            }
            else {

                while (current.getNext() != null) {
                    if (current.getKey().equals(key)) {
                        temp = current.getData();
                        current.setData(value);
                    }
                    current = current.getNext();
                }
            }
            return temp;
        }

        public void display () { //Outputs all items in Hashmap (should include key and value)
        //display is grey but method full functional
        /*
        if(!HashMap.isEmpty()) {
            // Displaying the HashMap
            System.out.println("HashMap:\n " + HashMap);
        }

         */
            for(int x = 0; x < buckets.length; x++) {//ArrayIndexOutOfBoundsException
                System.out.println("Bucket "+ x + "\n");
                Node current = buckets[x];

                while (current.getNext() != null) {
                    System.out.print("Data- "+current.getData()+"\n Key- "+current.getKey()+"\n");
                    current = current.getNext();
                }
                System.out.print("Data- "+current.getData()+"\n Key- "+current.getKey()+"\n");
                System.out.println("");
            }
        }

        public int calculateIndex (Object key){
            //index = hashCode(key) & (n-1)  (where n is the size of the Array)
            int index = key.hashCode() & (buckets.length-1);
            //System.out.println("the index for key" +key+" is "+index);
            return index;
        }


        public class Node {
            private Object key;
            private Object value;
            private int hash;
            private Node next;
            //What 4 variables does the node need to hold?
            //Key
            //Value
            //Hash
            //Pointer to next Node
            public Node(Object value, Object key) {

                this.key = key;
                this.value = value;
                next = null;
                if(key != null)
                hash = key.hashCode();
                System.out.println("New Node-> Hash: " + hash);
            }
// setters and getters
            public Object getData() {
                return value;
            }

            public Node getNext() {
                return next;
            }
/*
            public int getHash() {
                return hash;
            }//unused
            */
            public Object getKey() {
                return key;
            }

            public void setNext(Node next) {
                    this.next = next;
                  }

                  public void clearNext(){
                next = null;
            }

            public void setData(Object value){
                this.value = value;
            }

        }
    }

