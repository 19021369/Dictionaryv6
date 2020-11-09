package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;


public class Controller {
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView listView;

    @FXML
    private TextField textFieldE;
    @FXML
    private TextField textFieldV;
    @FXML
    private TextArea textArea1;

    @FXML
    private TextField textFieldE2;
    @FXML
    private TextArea textArea2;

    @FXML
    private ChoiceBox<String> choiceBox;
    ObservableList<String> list = FXCollections.observableArrayList("English", "Vietnamese");
    @FXML
    private TextField textFieldE3;
    @FXML
    private TextArea textAreaV3;
    @FXML
    private TextField textField3;
    @FXML
    private TextArea textArea3;
    String t;

    public void Search(ActionEvent event) {
        String s = textField.getText();
        textArea.setText(DictionaryManagement.dictionaryLookup(s));
    }

    public void listView(KeyEvent event) {
        String s = textField.getText();
        List<String> list = DictionaryCommandLine.dictionarySearcher(s);
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (String a : list) {
            temp.add(a);
        }
        listView.setItems(temp);
        list.clear();
        listView.getSelectionModel().selectIndices(0);
    }
    
    public void searching(MouseEvent event) {
        Object s = listView.getSelectionModel().getSelectedItem();
        t = (String) s;
        textArea.setText(DictionaryManagement.dictionaryLookup((String)s));
    }

    public void Add(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Save(MouseEvent event) throws IOException {
        Word S = new Word(textFieldE.getText(), textFieldV.getText());
        if(DictionaryManagement.dictionaryLookup(textFieldE.getText()) == "Unknown word") {
            Dictionary.addWord(S);
            textArea1.setText("Lưu thành công!");
            DictionaryManagement.dictionaryExportToFile();
        } else {
            textArea1.setText("Từ đã tồn tại!");
        }
    }

    public void Delete(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Delete1(MouseEvent event) throws IOException {
        textArea2.setText(DictionaryManagement.dictionaryDelete(textFieldE2.getText()));
        DictionaryManagement.dictionaryExportToFile();
    }
    public void Edit(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void ChoiceBox(){
        choiceBox.setItems(list);
    }

    public void Find(MouseEvent event){
        textAreaV3.setText(DictionaryManagement.dictionaryLookup(textFieldE3.getText()));
    }

    public void SaveEdit(MouseEvent event) throws IOException {
        if(choiceBox.getValue().equals("English")){
            textArea3.setText(DictionaryManagement.dictionaryEdit(textFieldE3.getText(), textField3.getText(), 0));
        } else {
            textArea3.setText(DictionaryManagement.dictionaryEdit(textFieldE3.getText(), textField3.getText(), 1));
        }
        DictionaryManagement.dictionaryExportToFile();
    }

    public void Speech(ActionEvent event){
        DictionaryManagement.speech(t);
    }

}
