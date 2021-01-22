import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.Utility;

import java.io.IOException;

public class UtilityTest {

    @BeforeClass
    public static void setup() {

    }

    @Test
    public void testIsStopWord() throws IOException {
        String word = "school";
        String capitalizedWord = "School"; //should handle case

        String stopWord = "would";
        String caseJumbledStopWord = "WoUld"; //should handle case

        Assert.assertFalse(Utility.isStopWord(word));
        Assert.assertFalse(Utility.isStopWord(capitalizedWord));

        Assert.assertTrue(Utility.isStopWord(stopWord));
        Assert.assertTrue(Utility.isStopWord(caseJumbledStopWord));
    }

}
