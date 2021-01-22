package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Kanarupan
 */
public class Utility {

    private static List<String> stopWords = new ArrayList();
    private static final String FILE_PATH = "stopwords.txt";

    /**
     * Loads stop words into a list from the file
     * Do it once to improve performance
     * Check whether the passed word is a stop word by comparing the list
     * The comparison is case insensitive
     *
     * @param word
     * @return
     * @throws IOException
     */
    public static boolean isStopWord(String word) throws IOException {
        if (stopWords.isEmpty()) {
            stopWords = Files.readAllLines(Paths.get(FILE_PATH));
        }

        return stopWords.contains(word.toLowerCase());
    }

}
