package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DictionaryCommandLine {

    static List<Word> arr = Dictionary.listword;

    public void showAllWords() {
        ListIterator<Word> itr = arr.listIterator();
        System.out.println("No" + "   | English" + "|    Vietnamese");
        int i = 1;
        while (itr.hasNext()) {
            Object obj = itr.next();
            if (obj instanceof Word) {
                Word st = (Word) obj;
                System.out.println(i + "   | " +  st.getWord_target() + "|    " + st.getWord_explain());
            }
            i++;
        }
    }
    public void dictionaryAdvanced(String word) throws IOException {
        DictionaryManagement.insertFromFile("C:\\Users\\gameh\\IdeaProjects\\DictionaryV6\\src\\Dictionaries.txt");
        showAllWords();
        DictionaryManagement.dictionaryLookup(word);
    }

    public static List dictionarySearcher(String SWord){
        List<String> s = new ArrayList<>();
        for(int i = 0; i< arr.size(); i++){
            if(arr.get(i).getWord_target().indexOf(SWord) == 0){
                s.add(arr.get(i).getWord_target());
            }
        }
        return s;
    }
}
