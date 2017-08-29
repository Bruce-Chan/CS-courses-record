import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestJoin {

    @Test
    public void jointTest() {
        /*  Fish Dogs Cats
             1    1    3
             2    1    4
             2    3    5
         */
        Column c0 = new Column("Fish",new int[]{1,2,2});
        Column c1 = new Column("Dogs",new int[]{1,1,3});
        Column c2 = new Column("Cats",new int[]{3,4,5});
        List<Column> cols1 = new ArrayList<>();
        cols1.add(c0);
        cols1.add(c1);
        cols1.add(c2);
        Table t1 = new Table(cols1);

        /*  Fish Dogs Pigs
             2    2    30
             2    3    40
            13    4    50
         */

        Column c5 = new Column("Fish",new int[]{2,2,13});
        Column cAct = new Column("Fish",new int[]{2,2,13});
        Column c3 = new Column("Dogs",new int[]{2,3,4});
        Column c4 = new Column("Pigs",new int[]{30,40,50});
        List<Column> cols2 = new ArrayList<>();
        cols2.add(c3);
        cols2.add(c4);
        cols2.add(c5);
        Table t2 = new Table(cols2);

        /*  Fish Dogs Cats Pigs
             2    3    5   30
         */

        c0 = new Column("Fish",new int[]{2});
        c1 = new Column("Dogs",new int[]{3});
        c2 = new Column("Cats",new int[]{5});
        c4 = new Column("Pigs",new int[]{40});
        List<Column> cols3 = new ArrayList<>();
        cols3.add(c0);
        cols3.add(c1);
        cols3.add(c2);
        cols3.add(c4);
        Table t3 = new Table(cols3);
        Table tActual = Table.join(t1,t2);
        Assert.assertEquals(t3,tActual);
    }

}
