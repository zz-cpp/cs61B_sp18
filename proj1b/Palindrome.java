public class Palindrome {

    /**
    * Return a Deque where the characters appear
    * in the same order as in the String.
    *
    * @param word a String need to be stored.
    * @return Deque<Character>
    */
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> res = new LinkedListDeque<>();
        char nth = ' ';
        for (int i = 0; i < word.length(); i++) {
            nth = word.charAt(i);
            res.addLast(nth);
        }
        return res;
    }

 /**
  * Return true if the word is palindrome ,otherwise return false.
  *
  * @param word a String need to be handled
  */
    public boolean isPalindrome(String word) {
        Deque<Character> characterDeque = wordToDeque(word);
        return isPalindromeHelp(characterDeque);
    }

    /**
    * isPalindrome help function with recursion
    * by comparing the two end element if is same.
    *
    * @param words a deque and type is Character.
    */
    private boolean isPalindromeHelp(Deque<Character> words) {

        if (words.size() == 0 || words.size() == 1) {
            return true;
        }

        Character left  = words.removeFirst();
        Character right = words.removeLast();

        if (!left.equals(right)) {
            return false;
        }

        return isPalindromeHelp(words);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {

        Deque<Character> characterDeque = wordToDeque(word);
        return isPalindromeHelp(characterDeque, cc);

    }

    private boolean isPalindromeHelp(Deque<Character> words, CharacterComparator cc) {

        if (words.size() == 0 || words.size() == 1) {
            return true;
        }

        Character left  = words.removeFirst();
        Character right = words.removeLast();

        if (!left.equals(right) && !cc.equalChars(left, right)) {
            return false;
        }

        return isPalindromeHelp(words, cc);

    }
}
