import java.util.Arrays;

/** Performs some basic Array Deque tests. */
public class ArrayDequeTest {
    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addFirst(100);
        int testNum = 32;
        for(int i = 0; i<testNum; i++ ){
            a.addLast(i);
        }
        a.printDeque();
        System.out.println(a.get(5));

        for(int i = 0; i<16; i++ ){
            a.removeLast();
        }
        a.printDeque();
        for(int i = 0; i<14; i++ ){
            a.removeLast();
        }
        a.printDeque();
        System.out.println(a.removeFirst());
        System.out.println(a.get(1));
        System.out.println(a.size());
        a.printDeque();
    }

}
