package lab9;

import java.lang.reflect.Array;
import java.util.*;

public class MyHashMap <K extends Comparable, V> implements Map61B<K, V> {
    private double loadF = 5.0; // default loadFactor
    private int capacity = 10; // default hash table size
    private int size; // number of key-value item
    Set<K> keySet;
    List<Item>[] arr; //array of chained lists of items

    private class Item<K,V> {
        K key;
        V value;

        Item(K k, V v){
            key = k;
            value = v;
        }
    }

    public MyHashMap() {
        initialize();
    }

    public MyHashMap(int initialSize) {
        capacity = initialSize;
        initialize();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        capacity = initialSize;
        loadF = loadFactor;
        initialize();
    }

    public void initialize(){
        arr = (List<Item>[]) new List[capacity];
        for(int i = 0; i<capacity; i++){
            arr[i] = new ArrayList<Item>();
        }
        keySet = new HashSet<>();
        size = 0;
    }

    /* distribute index for key */
    private int hash(K k){
        int index = k.hashCode()%capacity;
        if(index<0){
            index = capacity + index;
        }
        return index;
    }

    @Override
    public void clear() {
        initialize();
    }

    private void resize(int newCapacity){
        MyHashMap<K,V> temp = new MyHashMap<>(newCapacity,loadF);
        for(int i = 0; i < capacity; i++){
            List<Item> selectedLst = arr[i];
            for(Item item : selectedLst){
                temp.put((K)item.key,(V)item.value);
            }
        }
        this.capacity=temp.capacity;
        this.arr = temp.arr;
        this.keySet = temp.keySet;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int index = hash(key);
        List<Item> selectedLst = arr[index];
        for(Item item: selectedLst){
            if(item.key.equals(key)){
                item.value = value; // already contain this key
                return;
            }
        }
        /* do not contain this key */
        selectedLst.add(new Item(key,value));
        size++;
        keySet.add(key);
        float newloadFactor = size/(float) capacity;
        if(newloadFactor>=loadF){
            resize(2*capacity);
        }
    }

    @Override
    public V get(K key) {
        if(key == null){
            return null;
        }
        int index = hash(key);
        List<Item> selectedLst = arr[index];
        for(Item item: selectedLst){
            if(item.key.equals(key)){
                return (V) item.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key)!=null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }


    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
