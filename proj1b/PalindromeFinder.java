/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("words.txt");

        String longestWord = in.readString();
        int longestN = 0;
        for (int n = 0; n < 26; n++){
            in = new In("words.txt");
            while (!in.isEmpty()) {
                String word = in.readString();

                if (word.length() >= minLength && Palindrome.isPalindrome(word, new OffByN(n)) &&
                        word.length() >= longestWord.length()) {
                    longestWord = word;
                    longestN = n;
                }
            }
        }
        System.out.println("get the longest offByN palindrome: "+ longestWord+" for N equal to "+ longestN);


        in = new In("words.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, new OffByN(0))) {
                System.out.println(word);
            }
        }
    }
} 
