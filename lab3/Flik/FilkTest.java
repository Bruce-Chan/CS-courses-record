import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest{
    @Test
    public void NumEqualTest(){
        assertTrue(Flik.isSameNumber(3,3));
        assertTrue(Flik.isSameNumber(128,128));
    }

}