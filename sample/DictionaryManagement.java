package sample;

import com.sun.speech.freetts.VoiceManager;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class DictionaryManagement {

    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String temp = scan.nextLine();
        for (int i = 0; i < n; i++) {
            String En = scan.nextLine();
            String Vn = scan.nextLine();
            Word newWord = new Word(En, Vn);
            sample.Dictionary.addWord(newWord);
        }
    }

    public static void insertFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            int index = line.indexOf("\t");
            Word newWord = new Word(line.substring(0, index), line.substring(index + 1));
            Dictionary.addWord(newWord);
            line = reader.readLine();
        }
    }

    public static String dictionaryLookup(String word) {
        for (int i = 0; i < Dictionary.listword.size(); i++) {
            if (word.equals(Dictionary.listword.get(i).getWord_target())) {
                return Dictionary.listword.get(i).getWord_explain();
            }
        }
        return "Unknown word";
    }
    public static String dictionaryDelete(String word) {
        for (int i = 0; i < Dictionary.listword.size(); i++) {
            if (Dictionary.listword.get(i).getWord_target().equals(word)) {
                Dictionary.listword.remove(Dictionary.listword.get(i));
                return "Xóa thành công!";
            }
        }
        return "Không tìm thấy từ!";
    }

    public static String dictionaryEdit(String wword, String aword, int k) {
        if(k==0) {
            for (int i = 0; i < Dictionary.listword.size(); i++) {
                if (Dictionary.listword.get(i).getWord_target().equals(wword)
                        && DictionaryManagement.dictionaryLookup(aword) == "Unknown word"){
                    Dictionary.listword.get(i).setWord_target(aword);
                    return "Sửa thành công!";
                }
            }
            return "Từ này đã tồn tại";
        } else {
            for (int i = 0; i < Dictionary.listword.size(); i++) {
                if (Dictionary.listword.get(i).getWord_target().equals(wword)) {
                    Dictionary.listword.get(i).setWord_explain(aword);
                    return "Sửa thành công!";
                }
            }
        }
        return "sửa thành công!";
    }

    public static void speech(String text) {
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }

    public static void dictionaryExportToFile() throws IOException {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("C:\\Users\\gameh\\IdeaProjects\\DictionaryV6\\src\\Dictionaries.txt");
            for(int i = 0; i< Dictionary.listword.size(); i++) {
                String s = Dictionary.listword.get(i).getWord_target() + "\t" + Dictionary.listword.get(i).getWord_explain() + "\r\n";
                byte b[] = s.getBytes();
                fout.write(b);
            }
            fout.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // close file output stream
            fout.close();
        }
    }
}
