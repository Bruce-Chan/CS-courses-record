package db;

import java.util.ArrayList;
import java.util.List;

public class Row {
    public List<Object> items;
    public int size;

    /* Constructor */

    Row() {
        items = new ArrayList<>();
        size = 0;
    }

    void add(Object item){
        items.add(item);
        size++;
    }

    void add(Row r, List<Integer> targetIndexs) {
        for (int i :targetIndexs){
            add(r.items.get(i));
        }
    }

}
