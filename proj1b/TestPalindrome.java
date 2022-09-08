import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String A = "cat";
        String B = "aaaaab";
        String C = "aaaAaa";
        String D = "racecar";
        String E = "a";
        String F = "";

        assertFalse(palindrome.isPalindrome(A));
        assertFalse(palindrome.isPalindrome(B));
        assertFalse(palindrome.isPalindrome(C));
        assertTrue(palindrome.isPalindrome(D));
        assertTrue(palindrome.isPalindrome(E));
        assertTrue(palindrome.isPalindrome(F));
    }

    @Test
    public void testIsPalindromeWithCharacterComparator() {
        OffByOne obo = new OffByOne();
        String A = "flake";
        String B = "AECDFB";

        String D = "";
        String E = "shfkdugfudgfjue";

        assertTrue(palindrome.isPalindrome(A,obo));
        assertTrue(palindrome.isPalindrome(B,obo));
        assertTrue(palindrome.isPalindrome(D,obo));

        assertFalse(palindrome.isPalindrome(E,obo));

    }
}
