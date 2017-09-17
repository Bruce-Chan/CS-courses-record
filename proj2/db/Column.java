package db;

import java.util.ArrayList;
import java.util.List;

public class Column <T>{
    public String name;
    public String type;
    public List<T> items;
    public int size;


    /* Constructor */
    Column(String n, String ty) {
        name = n;
        items = new ArrayList<>();
        size = 0;
        type = ty;
    }

    void add(T item){
        items.add(item);
        size ++;
    }


}
