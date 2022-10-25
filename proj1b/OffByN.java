public class OffByN implements CharacterComparator{

    private int bet;

    public OffByN(int n)
    {
        bet=n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (int) (x - y) == bet || (int) (x - y) == -bet;
    }
}
