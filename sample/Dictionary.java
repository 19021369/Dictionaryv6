package sample;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public static List<Word> listword = new ArrayList<Word>();
    public static void addWord(Word newWord) {
        listword.add(newWord);
    }
}

