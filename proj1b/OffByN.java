public class OffByN implements CharacterComparator {

    private int NUM;


    public OffByN(int N) {
        this.NUM = N;
    }

    @Override
    public boolean equalChars(char x, char y) {

        if ((x - y) == NUM || (y - x) == NUM) {
            return true;
        }
        return false;

    }
}
