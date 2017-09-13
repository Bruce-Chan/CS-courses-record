import java.util.List;

public class Row{
    public int[] items;
    public int size;

    /* Constructor */
    Row (int[] arr){
        items = arr;
        size = arr.length;
    }

    Row (int num) {
        items = new int[num];
        size = 0;
    }

    public void add(int i){
        items[size] = i;
        size++;
    }

    public void addAll(int[] rowItems){
        for(int i = 0; i < rowItems.length; i++){
            add(rowItems[i]);
        }
    }
}
