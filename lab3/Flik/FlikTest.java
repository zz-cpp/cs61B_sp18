import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void testIsSameNumber (){
        Integer j = 0;
        for (Integer i = 0; i < 500 ; i++,j++) {

            assertTrue("i: " + i+ " " + "j: " + j + '\n',i==j);
        }

    }


}
