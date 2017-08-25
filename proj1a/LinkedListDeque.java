class LinkedListDeque<itemType>{

    private class ItemNode{
        private ItemNode prev;
        private ItemNode next;
        private itemType item;

        private ItemNode(ItemNode prevNode, itemType i, ItemNode nextNode){
            this.prev = prevNode;
            this.item = i;
            this.next = nextNode;
        }

        private itemType getReHelper(int index, int size) {

            if (index >= size) {
                return null;
            } else if (index == 0) {
                return this.next.item;
            } else {
                return this.next.getReHelper(index-1, size);
            }
        }

    }
    private ItemNode sentinel;
    private int size = 0;

    public LinkedListDeque(){

        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(itemType i){
        sentinel = new ItemNode(null, null, null);
        ItemNode element = new ItemNode(sentinel, i, sentinel);
        sentinel.prev = element;
        sentinel.next = element;
        size += 1;
    }

    public int size(){
        return size;
    }

    public void addFirst(itemType i){
        ItemNode iNext = sentinel.next;
        ItemNode firstItem = new ItemNode(sentinel, i, iNext);
        iNext.prev = firstItem;
        sentinel.next = firstItem;
        size++;
    }

    public void addLast(itemType i){
        ItemNode iPrev = sentinel.prev;
        ItemNode lastItem = new ItemNode(iPrev, i, sentinel);
        iPrev.next = lastItem;
        sentinel.prev = lastItem;
        size++;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public itemType removeFirst(){
        if (size == 0){
            return null;
        }
        ItemNode removeItem = sentinel.next;
        sentinel.next = removeItem.next;
        removeItem.next.prev = sentinel;
        size--;
        return removeItem.item;
    }

    public itemType removeLast(){
        if(size == 0){
            return null;
        }
        ItemNode removeItem = sentinel.prev;
        sentinel.prev = removeItem.prev;
        removeItem.prev.next = sentinel;
        size--;
        return removeItem.item;
    }

    public itemType get(int index){
        itemType result = null;
        ItemNode pointer = sentinel.next;
        for(int i = index; i>=0 && index<size; i--){
            result = pointer.item;
            pointer = pointer.next;
        }
        return result;
    }

    public itemType getRecursive(int index){
        return sentinel.getReHelper(index,size);
    }

    public void printDeque(){
        ItemNode pointer = sentinel.next;
        for (int i=0; i<size; i++){
            System.out.print(pointer.item+" ");
            pointer = pointer.next;
        }

    }




}
