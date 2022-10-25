public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        return (int) (x - y) == 1 || (int) (x - y) == -1;
    }

    public boolean isPalindrome(String word){
        for(int i=0;i<word.length()/2;i++)
        {
            if(!equalChars(word.charAt(i),word.charAt(word.length()-1-i)))return false;
        }
        return true;
    }
}
