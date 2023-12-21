package com.my.utils.java;


public class MyHashMap<K,V> {

    public class Node<K,V>{
        V value;
        K key;
        Node<K,V> next=null;
        public Node(K key, V value, Node<K, V> next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }

    }

    static final int DEFAULT_CAPACITY=10;
    Node<K,V>[] store;
    int capacity;
    //    元素个数>loadFactor*capacity时扩容
    float loadFactor;
    int size;
    public MyHashMap(){
        this(DEFAULT_CAPACITY);
    }
    public MyHashMap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.loadFactor = 0.75F;
        this.store =  (Node<K,V>[]) new Node[capacity];
    }

    private Node insertNodeAtHead(Node LinkListHead, Node node){
        node.next = LinkListHead;
        node = LinkListHead;
        return node;
    }
    public V put(K key,V value){
        int index = key.hashCode() % this.capacity;
        Node node = this.store[index];

        if(node==null){
            this.store[index] = new Node<>(key, value, null);
        }else{
            while (node.next!=null){
                if(node.key == key){
                    node.value = value;
                    return value;
                }
                node = node.next;
            }
        }

        node.next = new Node<>(key, value, null);
        return value;
    }

    public V get(K key){
        throw new RuntimeException("Not");
    }

    public V remove(K key){
        throw new RuntimeException("Not");
    }

    public static void main(String[] args) {
        MyHashMap<String,Integer> hashMap1 = new MyHashMap<>();
        hashMap1.put("a",1);
        hashMap1.put("b",2);
        hashMap1.put("c",3);
        hashMap1.put("a",5);
//        // 5
//        System.out.println(hashMap1.get("a"));
//        // 5
//        System.out.println(hashMap1.remove("a"));
//        // null
//        System.out.println(hashMap1.get("a"));
    }
}
