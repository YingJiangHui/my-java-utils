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
    private void rehash(int newCapacity){
        this.capacity = Math.max(newCapacity,DEFAULT_CAPACITY);
//          所有元素需要移动到新的store中
        Node<K,V>[] newStore = (Node<K,V>[]) new Node[this.capacity];
        for (Node<K,V> node: this.store){
            while (node!=null){
//                    头插法位置交换过程
                Node<K,V> nextNode = node.next;
                int index = hash(node.key);
                Node<K,V> headNode = newStore[index];
                newStore[index] = node;
                node.next = headNode;

                node = nextNode;
            }
        }

        this.store = newStore;
    }
    private void expandCapacity(){

        if(this.size+1>this.loadFactor*this.capacity){
            int newCapacity = Math.round((1+this.loadFactor)*this.capacity);
            this.rehash(newCapacity);
        }
    }
    private void reduceCapacity(){
        int newCapacity = (int) Math.floor(this.capacity/(1+this.loadFactor));
        if(this.size-1<this.loadFactor*newCapacity){
            this.rehash(newCapacity);
        }
    }
    public V put(K key,V value){
//        不包含key，说明需要新增，一次查询可优化
        if(!this.containsKey(key)){
            this.expandCapacity();
        }
        return this.put(this.store,key,value);
    }
    private V put(Node<K,V>[] store,K key,V value){
//        无法在该函数的插入操作之前直接扩容，因为store是外部传入的
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
//    遍历处理剩余节点，尾插法
        while (node.next!=null){
            if(node.next.key == key){
                node.next.value = value;
                return value;
            }
            node = node.next;
        }
        node.next = new Node<>(key, value, null);
        this.size++;
        return value;
    }
    private Node<K,V>getNode(K key){
        Node<K,V> node = this.store[hash(key)];
        while (node!=null){
            if(node.key ==key){
                return node;
            }else{
                node = node.next;
            }
        }
        return null;
    }

    public V get(K key){
        return getNode(key).value;
    }

    public boolean containsKey(K key){
        return getNode(key)!=null;
    }

    public V remove(K key){
        Node<K,V> node = this.store[hash(key)];
//        不为空再处理
        if(node!=null){
//            处理头节点
            if(node.key == key){
                this.size--;
                this.store[hash(key)]=node.next;
                this.reduceCapacity();
                return node.value;
            }
//处理剩余节点
            while (node.next!=null){
                if(node.next.key ==key){
                    this.size--;
                    Node<K,V> nextNode = node.next;
                    node.next = node.next.next;
                    this.reduceCapacity();
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
        hashMap1.put("z",6);
        hashMap1.put("h",7);
        hashMap1.put("p",8);
        hashMap1.put("o",9);
        hashMap1.put("l",5);
        Arrays.stream(hashMap1.store).forEach((o)-> System.out.println(o));
        System.out.println("capacity: "+hashMap1.capacity);
        System.out.println("l hash is "+ "l".hashCode()%18);
        System.out.println("b: "+hashMap1.get("b"));
        System.out.println("c："+hashMap1.get("c"));
        System.out.println("a："+hashMap1.get("a"));
        System.out.println("l："+hashMap1.get("l"));
        System.out.println("z："+hashMap1.get("z"));
        System.out.println("h："+hashMap1.get("h"));
        System.out.println("p："+hashMap1.get("p"));
        System.out.println("o："+hashMap1.get("o"));
        System.out.println("l："+hashMap1.get("l"));

        hashMap1.remove("a");
        hashMap1.remove("b");
        hashMap1.remove("c");
        hashMap1.remove("p");
        hashMap1.remove("o");
//        System.out.println("capacity: "+hashMap1.capacity);
        Arrays.stream(hashMap1.store).forEach((o)-> System.out.println(o));
    }
}
