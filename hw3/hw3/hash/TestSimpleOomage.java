package hw3.hash;

import org.junit.Assert;
import org.junit.Test;


import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(5, 20, 10);
        SimpleOomage ooC = new SimpleOomage(10, 5, 20);
        SimpleOomage ooD = new SimpleOomage(10, 20, 5);
        SimpleOomage ooE = new SimpleOomage(20, 5, 10);
        SimpleOomage ooF = new SimpleOomage(20, 10, 5);

        HashSet<SimpleOomage> set = new HashSet<>();
        set.add(ooA);
        set.add(ooB);
        set.add(ooC);
        set.add(ooD);
        set.add(ooE);
        set.add(ooF);

        SimpleOomage[] test = new SimpleOomage[set.size()];
        Object[] objects = set.toArray();

        for (int i = 0; i < set.size(); i++) {
            test[i] = (SimpleOomage) objects[i];
        }

        for (int i = 0; i < test.length; i++) {
            for (int j = i + 1; j < test.length; j++) {
                assertFalse(test[i].equals(test[j]));
                assertNotEquals(test[i], test[j]);
            }
        }

        assertTrue(set.contains(ooA));


    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
