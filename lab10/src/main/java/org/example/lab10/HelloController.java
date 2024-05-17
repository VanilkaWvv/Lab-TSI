package org.example.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelloController {
    Vigenere code = new Vigenere();
    @FXML
    private TextArea input;
    @FXML
    private TextArea key;
    @FXML
    private TextArea output;
    @FXML
    private TextArea keyAfterAtack;
    @FXML
    protected void encrypt() {
        String inputText = input.getText();
        String keyText = key.getText();
        String outputText = code.encrypt(inputText,keyText);
        if (outputText.isEmpty()) output.setText("Nu a fost introdus textul sau keya!");
        else output.setText(outputText);
    }
    @FXML
    protected void decrypt() {
        String inputText = input.getText();
        String keyText = key.getText();
        String outputText = code.decrypt(inputText,keyText);
        if (outputText.isEmpty()) output.setText("Nu a fost introdus textul sau keya!");
        else output.setText(outputText);
    }
    @FXML
    protected void atack(){
        String inputText = input.getText();
        VigenereAnalize analize = new VigenereAnalize();
        if (inputText.isEmpty()) keyAfterAtack.setText("Nu este introdus textul!");
        else keyAfterAtack.setText(inputText);
    }
}