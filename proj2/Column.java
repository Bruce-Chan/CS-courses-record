public class Column {
    public String name;
    public int[] items;
    public int size;

    public Column(String n, int num) {
        name = n;
        items = new int[num];
        size = 0;
    }

    public Column(String n, int[] ints) {
        name = n;
        items = ints;
        size = items.length;
    }

    public void add(int item){
        items[size]=item;
        size ++;
    }

}
