import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testRun(){
        assertTrue(offByOne.equalChars('r','s'));
        assertTrue(offByOne.equalChars('b','a'));
        assertFalse(offByOne.equalChars('b','b'));
        assertFalse(offByOne.equalChars('e','b'));
    }
}
