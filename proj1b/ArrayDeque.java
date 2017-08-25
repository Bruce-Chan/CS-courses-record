class ArrayDeque<Item> implements Deque<Item>{
    private int nextFirst;
    private int nextLast;
    private int size;
    private Item[] items;

    public ArrayDeque(){
        items = (Item[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    private int minusOne(int index){
        if (index == 0){
            return items.length-1;
        }
        return index-1;
    }

    private int addOne(int index){
        if (index == items.length-1){
            return 0;
        }
        return index+1;
    }

    private void moveItemsTo(Item[] targetArray){
        int lenFirstToEnd = items.length-addOne(nextFirst);
        System.arraycopy(items,addOne(nextFirst),targetArray,0,lenFirstToEnd);
        int restLen = size - lenFirstToEnd;
        System.arraycopy(items,0,targetArray,lenFirstToEnd,restLen);
        items = targetArray;
        nextFirst = items.length-1;
        nextLast = size;
    }

    private void resize(){
        if(size == items.length){
            Item[] biggerItems = (Item[]) new Object[2*size];
            moveItemsTo(biggerItems);
        }else if (items.length >= 16 && size < items.length/4){
            int targetLen = items.length;
            while(targetLen > 8 && size<targetLen/4) {
                targetLen = targetLen / 2;
            }
            Item[] smallerItems = (Item[]) new Object[targetLen];
            if (addOne(nextFirst) + size <= items.length){
                System.arraycopy(items,addOne(nextFirst),smallerItems,0,size);
                items = smallerItems;
                nextFirst = items.length-1;
                nextLast = size;
            }else{
                moveItemsTo(smallerItems);
            }
        }

    }

    @Override
    public void addFirst(Item i){
        items[nextFirst]=i;
        nextFirst = minusOne(nextFirst);
        size++;
        resize();
    }

    @Override
    public void addLast(Item i){
        items[nextLast]=i;
        nextLast = addOne(nextLast);
        size++;
        resize();
    }

    @Override
    public Item removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        int removeIndex = addOne(nextFirst);
        Item removeItem = items[removeIndex];
        items[removeIndex] = null;
        nextFirst = removeIndex;
        size--;
        resize();
        return removeItem;
    }

    @Override
    public Item removeLast(){
        if(this.isEmpty()){
            return null;
        }
        int removeIndex = minusOne(nextLast);
        Item removeItem = items[removeIndex];
        items[removeIndex] = null;
        nextLast = removeIndex;
        size--;
        resize();
        return removeItem;
    }

    @Override
    public Item get(int index){
        if (index >= size){
            return null;
        }
        int arrayIndex = nextFirst+1+index;
        if (arrayIndex >= items.length){
            arrayIndex = arrayIndex - items.length;
        }
        return items[arrayIndex];
    }

    @Override
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int dequeIndex;
        int arrayIndex;
        for(dequeIndex = 0, arrayIndex = addOne(nextFirst); dequeIndex<size; dequeIndex++){
            System.out.print(items[arrayIndex]+" ");
            arrayIndex = addOne(arrayIndex);
        }
    }
}
