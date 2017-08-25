public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int n = x - y;
        if (n == 1 || n == -1){
            return true;
        }
        return false;
    }
}
