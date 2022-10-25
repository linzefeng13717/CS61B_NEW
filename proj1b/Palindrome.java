import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> WordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++)
            WordDeque.add(word.charAt(i));
        return WordDeque;
    }
    public boolean isPalindrome(String word){
        for(int i=0;i<word.length()/2;i++)
        {
            if(word.charAt(i)!=word.charAt(word.length()-i-1))return false;
        }
        return true;
    }

}
