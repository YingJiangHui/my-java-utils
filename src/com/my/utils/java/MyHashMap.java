package com.my.utils.java;


import java.util.Arrays;

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

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", key=" + key +
                    ", next=" + next +
                    '}';
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
    private int hash(K key){
        return key.hashCode() % this.capacity;
    }
    private void expandCapacity(){
        if(this.size+1>this.loadFactor*this.capacity){
//            扩容
            this.capacity = Math.round((1+this.loadFactor)*this.capacity);
//          所有元素需要移动到新的store中
            Node<K,V>[] newStore = (Node<K,V>[]) new Node[this.capacity];
            for (Node<K,V> node: this.store){
                while (node!=null){
//                    位置交换过程
                    Node<K,V> nextNode = node.next;

                    Node<K,V> headNode = newStore[hash(node.key)];
                    newStore[hash(node.key)] = node;
                    node.next = headNode;

                    node = nextNode;
                }
            }

            this.store = newStore;
        }
    }
    public V put(K key,V value){
        return this.put(this.store,key,value);
    }
    private V put(Node<K,V>[] store,K key,V value){

        Node node = store[hash(key)];
//        先处理头节点
        if(node==null){
            store[hash(key)] = new Node<>(key, value, null);
            this.size++;
            return value;
        }else{
            if(node.key==key){
                node.value = value;
                return value;
            }
        }
//    遍历处理剩余节点
        while (node.next!=null){
            if(node.next.key == key){
                node.next.value = value;
                return value;
            }
            node = node.next;
        }
        this.expandCapacity();
        node.next = new Node<>(key, value, null);
        this.size++;
        return value;
    }

    public V get(K key){
        Node<K,V> node = this.store[hash(key)];
        while (node!=null){
            if(node.key ==key){
                return node.value;
            }else{
                node = node.next;
            }
        }
        return null;
    }

    public V remove(K key){
        Node<K,V> node = this.store[hash(key)];
//        不为空再处理
        if(node!=null){
//            处理头节点
            if(node.key == key){
                this.size--;
                this.store[hash(key)]=node.next;
                return node.value;
            }
//处理剩余节点
            while (node.next!=null){
                if(node.next.key ==key){
                    Node<K,V> nextNode = node.next;
                    node.next = node.next.next;
                    return nextNode.value;
                }else{
                    node = node.next;
                }
            }
        }


        return null;
    }

    public static void main(String[] args) {
        MyHashMap<String,Integer> hashMap1 = new MyHashMap<>();
        hashMap1.put("a",1);
        hashMap1.put("b",2);
        hashMap1.put("c",3);
        hashMap1.put("a",4);
        hashMap1.put("l",5);
        hashMap1.put("z",6);
        hashMap1.put("h",7);
        hashMap1.put("p",8);
        hashMap1.put("o",9);
        Arrays.stream(hashMap1.store).forEach((o)-> System.out.println(o));

        System.out.println(hashMap1.get("b")==2);
        System.out.println(hashMap1.get("c")==3);
        System.out.println(hashMap1.get("a")==4);
        System.out.println(hashMap1.get("l")==5);
        System.out.println(hashMap1.get("z")==6);
        System.out.println(hashMap1.get("h")==7);
        System.out.println(hashMap1.get("p")==8);
        System.out.println(hashMap1.get("o")==9);

        hashMap1.remove("a");
        hashMap1.remove("b");
        hashMap1.remove("c");
        hashMap1.remove("a");
        hashMap1.remove("l");
        hashMap1.remove("z");
        hashMap1.remove("h");
        hashMap1.remove("p");
        hashMap1.remove("o");
        Arrays.stream(hashMap1.store).forEach((o)-> System.out.println(o));
    }
}
