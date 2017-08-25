import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void addFirstTest(){
        StudentArrayDeque<Integer> aDequeSt = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> aDequeSo = new ArrayDequeSolution<>();

        OperationSequence fs = new OperationSequence();


        int eleNum = StdRandom.uniform(10);
        for (int i = 0; i < eleNum; i++) {
            int n = StdRandom.uniform(100);
            aDequeSt.addFirst(n);
            fs.addOperation(new DequeOperation("addFirst", n));
            aDequeSo.addFirst(n);
        }

        for (int i = 0; i < eleNum; i++) {
            int n = StdRandom.uniform(100);
            aDequeSt.addLast(n);
            fs.addOperation(new DequeOperation("addLast", n));
            aDequeSo.addLast(n);
        }
        fs.addOperation((new DequeOperation("size")));
        assertEquals(aDequeSo.size(),aDequeSt.size());

        for (int i = 0; i < eleNum; i++) {
            int index = StdRandom.uniform(eleNum);
            fs.addOperation(new DequeOperation("get", index));
            assertEquals(fs.toString(),aDequeSo.get(index),aDequeSt.get(index));
        }

        for (int i = 0; i < eleNum; i++) {
            Integer a1 = aDequeSo.removeLast();
            fs.addOperation(new DequeOperation("removeLast"));
            Integer a2 = aDequeSt.removeLast();
            assertEquals(fs.toString(),a1,a2);
            Integer b1 = aDequeSo.removeFirst();
            fs.addOperation(new DequeOperation("removeFirst"));
            Integer b2 = aDequeSt.removeFirst();
            assertEquals(fs.toString(),b1,b2);
        }



    }
}
