public class Palindrome {

    public static Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private static boolean PalindromeHelper(Deque<Character> deque){
        if(deque.size() == 0 || deque.size() == 1){
            return true;
        }else if (deque.removeFirst() == deque.removeLast()){
            return PalindromeHelper(deque);
        }
        return false;
    }

    private static boolean PalindromeHelper(Deque<Character> deque, CharacterComparator cc){
        if(deque.size() == 0 || deque.size() == 1){
            return true;
        }else if (cc.equalChars(deque.removeFirst(),deque.removeLast())) {
            return PalindromeHelper(deque, cc);
        }
        return false;
    }

    public static boolean isPalindrome(String word){
        Deque<Character> deque = wordToDeque(word);
        return PalindromeHelper(deque);
    }

    public static boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        return PalindromeHelper(deque, cc);
    }


    public static void main(String[] args){
        int[] a = new int[3];
        a[0]=3;
        a[1]=2;
        a[2]=1;
        System.out.println(a[-2]);
    }

}
