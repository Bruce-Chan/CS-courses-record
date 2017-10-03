package lab8;

import java.util.*;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node {
        K key;
        V value;
        Node left, right, parent;

        public Node(K k, V v) {
            this.key = k;
            this.value = v;
            left = null;
            right = null;
        }
    }

    public Node root;
    int size;

    public BSTMap(K k, V v) {
        this.root = new Node(k, v);
        size = 1;
    }

    public BSTMap() {
        this.root = null;
        size = 0;
    }


    V get (Node n, K k) {
        if (n == null || k == null) {
            return null;
        }
        int comp = n.key.compareTo(k);
        if (comp < 0) {
            return get(n.right, k);
        } else if (comp > 0) {
            return get(n.left, k);
        } else {
            return n.value;
        }
    }

    Node put (Node n, K k, V value) {
        if (n == null){
            size++;
            return new Node(k,value);
        }
        int comp = n.key.compareTo(k);
        if(comp < 0){
            n.right = put(n.right, k, value);
        } else if(comp>0){
            n.left = put(n.left, k , value);
        } else {
            n.value = value;
        }
        return n;
    }

    private Node max(Node n){
        if (n.right == null){
            return n;
        } else {
            return max(n.right);
        }
    }

    private Node min(Node n){
        if (n.left == null){
            return n;
        } else {
            return min(n.left);
        }
    }
    private void keys(Node min, Node max, Node curr, Deque q){
        if(curr == null){
            return;
        }
        if(curr.key.compareTo(min.key)>0){
            keys(min,max,curr.left,q);
        }
        if (curr.key.compareTo(min.key)>=0 &&
                curr.key.compareTo(max.key)<=0){
            q.add(curr.key);
        }
        if (curr.key.compareTo(max.key)<0){
            keys(min,max,curr.right,q);
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return get(key)!=null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     **/
    @Override
    public V get(K key) {
       return get(root, key);
    }

    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        root = put(root, key, value);
    }


    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        Set<K> set = new HashSet<>();
        Iterator itor = iterator();
        while(itor.hasNext()){
            set.add((K) itor.next());
        }
        return set;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = remove(root,key);
        return value;
    }

    private Node remove(Node n, K key) {
        if(key == null){
            return null;
        }
        if(key.equals(n.key)){
            size--;
            n = createNew(n);
        } else if(n.key.compareTo(key)<0){
            n.right = remove(n.right,key);
        } else{
            n.left = remove(n.left,key);
        }
        return n;
    }

    private Node createNew(Node n) {
        if(n.left != null){
            max(n.left).right = n.right;
            return n.left;
        } else {
            return n.right;
        }
    }

    private Node remove(Node n, K key, V value) {
        if(key == null){
            return null;
        }
        if(key.equals(n.key)&&value.equals(n.value)){
            size--;
            n = createNew(n);
        } else if(n.key.compareTo(key)<0){
            n.right = remove(n.right,key);
        } else{
            n.left = remove(n.left,key);
        }
        return n;
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        root = remove(root,key);
        return value;
    }

    @Override
    public Iterator iterator(){
        Deque<K> dq = new ArrayDeque<>();
        keys(min(root),max(root),root,dq);
        return dq.iterator();
    }

}