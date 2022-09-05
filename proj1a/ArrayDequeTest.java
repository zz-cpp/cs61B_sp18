import org.junit.Test;
import static  org.junit.Assert.*;


public class ArrayDequeTest {

    @Test
    public void testAddRemove (){
        Integer[] pretend = new Integer[8];
        ArrayDeque<Integer> test = new ArrayDeque<>();
        // last : 1,2,3,4; first: 5,6,7,8;  => 1,2,3,4,8,7,6,5
        for (int i = 0; i < 4 ; i++) {
            pretend[i] = i + 1;
        }

        int count = 5;
        for (int i = 7; i > 3 ; i--) {
            pretend[i] = count;
            count++;
        }

        for (int i = 0; i < 8; i++) {
            if(i < 4){
                test.addLast(i + 1);
            }
            if(i >=4){
                test.addFirst(i + 1);
            }
        }

        assertArrayEquals(pretend, test.getItem());

        test.removeFirst();
        test.removeFirst();
        test.removeLast();

        pretend[3] = null;
        pretend[4] = null;
        pretend[5] = null;

        assertArrayEquals(pretend,test.getItem());
    }

    @Test
    public void testAddResize(){
        Integer[] pretend = new Integer[16];
        ArrayDeque<Integer> test = new ArrayDeque<>();

        for (int i = 4; i < 9 ; i++) {
            pretend[i] = i - 3;
        }

        int count = 8;
        for (int i = 0; i < 4 ; i++) {
            pretend[i] = count;
            count--;
        }

        for (int i = 0; i < 8; i++) {
            if(i < 4){
                test.addLast(i + 1);
            }
            if(i >=4){
                test.addFirst(i + 1);
            }
        }

        test.addLast(5);
        assertArrayEquals(pretend,test.getItem());
    }

    @Test
    public void testResizeArrayDeque (){
        Integer[] pretend = new Integer[201];
        ArrayDeque<Integer> test = new ArrayDeque<>();

        for (int i = 1; i < 201 ; i++) {
            if(i > 50)
                continue;
            pretend[i] = i;
        }

        int count = 91;
        for (int i = 200; i > 190; i--) {
            pretend[i] = count;
            count++;
        }

        for (int i = 1; i < 101; i++) {
            if(i > 50 && i <= 90  ){
                continue;
            }

            if(i >= 91 ){
                test.addFirst(i);
                continue;
            }
            test.addLast(i);
        }

        assertArrayEquals(pretend, test.getItem());
    }

}

