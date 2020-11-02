package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

//red for no reason works fine
        MyHashMap hashMap = new MyHashMap();
        //System.out.println("hashMap is empty: "+hashMap.isEmpty());
        System.out.println(hashMap.size());
        hashMap.put("Cat", "Mewo");
        hashMap.put("Cat", "Hiss");
        hashMap.put("Dog", "Woof");
        hashMap.put("Dog", "Woof");
        hashMap.put("Bird", "Chirp");
        hashMap.put("Bird", "Chirp");
        hashMap.put("DOLPHIN", "EEEEE");
        hashMap.putIfAbsent("Bird","Chirp");
        //System.out.println(hashMap.remove("Dog","I HATE THIS"));
        System.out.println(hashMap.getOrDefault("DOLPHIN","Apple SauZe"));
        hashMap.display();
    }
}
