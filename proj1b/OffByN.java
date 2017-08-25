public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int num){
        n = num;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == n || diff == -n){
            return true;
        }
        return false;
    }
}
