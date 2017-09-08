package synthesizer;

import java.util.Iterator;

public interface BoundedQueue <T> extends Iterable <T>{
    @Override
    Iterator<T> iterator();

    /**
     * return size of the buffer
     */
    int capacity();

    /**
     * return number of items currently in the buffer
     */
    int fillCount();

    /**
     * add item x to the end
     */
    void enqueue(T x);

    /**
     * delete and return item from the front
     */
    T dequeue();

    /**
     * return (but do not delete) item from the front
     */
    T peek();

    /**
     * check if the queue is empty
     */
    default  boolean isEmpty(){
        if(fillCount() == 0){
            return true;
        }
        return false;
    }

    /**
     * is the buffer full (fillCount is same as capacity)?
     */

    default boolean isFull(){
        if(fillCount() == capacity()){
            return true;
        }
        return false;
    }
}
