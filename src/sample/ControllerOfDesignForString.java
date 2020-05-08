package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ControllerOfDesignForString implements Initializable
{
    private String algorithmName = null;
    private String keyFromTextFieldForKey = null;

    @FXML
    private ComboBox<String> comboBoxForAlgorithmNames;
    @FXML
    private TextField passwordFieldForKey, cipherTextField, plainTextField;
    @FXML
    private AnchorPane encryptDecryptAnchorPane;
    @FXML
    private RadioButton encryptRadioButton;
    @FXML
    private RadioButton decryptRadioButton;
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<String> algorithmNameList;
        algorithmNameList = FXCollections.observableArrayList("AES/ECB", "Vernam", "Shift", "OneTimePad", "Vigenere", "Mono Alphabetic");
        comboBoxForAlgorithmNames.setItems(algorithmNameList);
    }
    public  void selectAlgorithmName(ActionEvent event)
    {
        algorithmName = comboBoxForAlgorithmNames.getValue();
        encryptDecryptAnchorPane.setDisable(false);
        switch (algorithmName)
        {
            case "AES/ECB":
            {
                passwordFieldForKey.setDisable(false);
                passwordFieldForKey.setPromptText("Enter the key of length exactly equal to 16 characters.");
            }
            break;
            case "Vernam":
            {
                passwordFieldForKey.setDisable(false);
                passwordFieldForKey.setPromptText("Key should consists of small letters only.");
            }
            break;
            case "Shift":
            {
                passwordFieldForKey.setDisable(false);
                passwordFieldForKey.setPromptText("Key should consists of 0 - 25 numbers only.");
            }
            break;
            case "OneTimePad":
            {
                passwordFieldForKey.setDisable(false);
                passwordFieldForKey.setPromptText("Key should consists of small letters only.");
            }
            break;
            case "Vigenere":
            {
                passwordFieldForKey.setDisable(false);
                passwordFieldForKey.setPromptText("Key should consists of small letters only.");
            }
            break;
            case "Mono Alphabetic":
            {
                passwordFieldForKey.setDisable(true);
                passwordFieldForKey.setPromptText("");
            }
            break;
        }
    }
    public void choosingTheRadioButton(ActionEvent event)
    {
        if(encryptRadioButton.isSelected())
        {
            plainTextField.setText("");
            cipherTextField.setText("");
            encryptButton.setDisable(false);
            decryptButton.setDisable(true);
            plainTextField.setEditable(true);
            cipherTextField.setEditable(false);
        }
        else if (decryptRadioButton.isSelected())
        {
            plainTextField.setText("");
            cipherTextField.setText("");
            encryptButton.setDisable(true);
            decryptButton.setDisable(false);
            plainTextField.setEditable(false);
            cipherTextField.setEditable(true);
        }
    }
    public void encryptTheString(ActionEvent event)
    {
        switch (algorithmName)
        {
            case "AES/ECB":
            {
                //passwordFieldForKey.setDisable(false);
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                int lengthOfKey = keyFromTextFieldForKey.length();
                if (lengthOfKey != 16)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Length of Key");
                    alert.setHeaderText("AES/ECB key length");
                    alert.setContentText("Length of key must be equal to 16 characters as its length is 128-bit.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = AES_ECB_PKCS5Padding_String.encrypt(plainText, keyFromTextFieldForKey);
                    cipherTextField.setText(cipherText);
                }
            }
            break;
            case "Vernam":
            {
                //passwordFieldForKey.setDisable(false);
                boolean plainTextChecker = Pattern.matches("[a-zA-Z]+", plainTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Key Error");
                    alert.setHeaderText("Vernam Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.");
                    alert.showAndWait();
                }
                else if (!plainTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Plain Text Error");
                    alert.setHeaderText("Vernam Plain Text");
                    alert.setContentText("Plain text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = Vernam.encrypt(plainText, keyFromTextFieldForKey);
                    cipherTextField.setText(cipherText);
                }
            }
            break;
            case "Shift":
            {
                //passwordFieldForKey.setDisable(false);
                boolean plainTextChecker = Pattern.matches("[a-zA-Z]+", plainTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[0-9]+", keyFromTextFieldForKey);
                if (key)
                {
                    if (Integer.parseInt(keyFromTextFieldForKey) >= 0 && Integer.parseInt(keyFromTextFieldForKey) <= 25)
                        key = true;
                    else key = false;
                }
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shift Key Error");
                    alert.setHeaderText("Shift Cipher Key");
                    alert.setContentText("Key should consists of 0 - 25 numbers only.\nIt should not have space in it.");
                    alert.showAndWait();
                }
                else if (!plainTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shift Plain Text Error");
                    alert.setHeaderText("Shift Plain Text ");
                    alert.setContentText("Plain text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = Caesar.encrypt(plainText, Integer.parseInt(keyFromTextFieldForKey));
                    cipherTextField.setText(cipherText);
                }
            }
            break;
            case "OneTimePad":
            {
                //passwordFieldForKey.setDisable(false);
                boolean plainTextChecker = Pattern.matches("[a-zA-Z]+", plainTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                if (key)
                {
                    if (keyFromTextFieldForKey.length() != plainTextField.getText().length()) key = false;
                }
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("OneTimePad Key Error");
                    alert.setHeaderText("OneTimePad Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.\nThe length of key should be equal to length of plain text.");
                    alert.showAndWait();
                }
                else if (!plainTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Plain Text Error");
                    alert.setHeaderText("Vernam Plain Text");
                    alert.setContentText("Plain text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = OneTimePad.encrypt(plainText, keyFromTextFieldForKey);
                    cipherTextField.setText(cipherText);
                }
            }
            break;
            case "Vigenere":
            {
                //passwordFieldForKey.setDisable(false); abcdefghijklmnopqrstuvwxyz
                boolean plainTextChecker = Pattern.matches("[a-zA-Z]+", plainTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigenere Key Error");
                    alert.setHeaderText("Vigenere Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.");
                    alert.showAndWait();
                }
                else if (!plainTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigenere Plain Text Error");
                    alert.setHeaderText("Vigenere Plain Text");
                    alert.setContentText("Plain text should consist of alphabets only without space.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = VigenereCipher.encrypt(plainText, keyFromTextFieldForKey);
                    cipherTextField.setText(cipherText);
                }
            }
            break;
            case "Mono Alphabetic":
            {
                boolean plainTextChecker = Pattern.matches("[a-z]+", plainTextField.getText());
                //passwordFieldForKey.setDisable(true);
                //boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                /*if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Key Error");
                    alert.setHeaderText("Vernam Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.");
                    alert.showAndWait();
                }*/
                if (!plainTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mono Alphabetic Plain Text Error");
                    alert.setHeaderText("Mono Alphabetic Plain Text");
                    alert.setContentText("Plain text should consist of small alphabets only without space.");
                    alert.showAndWait();
                }
                else
                {
                    String plainText = plainTextField.getText();
                    String cipherText = MonoalphabeticCipher.encrypt(plainText);
                    cipherTextField.setText(cipherText);
                }
            }
            break;
        }
    }
    public void decryptTheString(ActionEvent event)
    {
        switch (algorithmName)
        {
            case "AES/ECB":
            {
                //passwordFieldForKey.setDisable(false);
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                int lengthOfKey = keyFromTextFieldForKey.length();
                if (lengthOfKey != 16)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Length of Key");
                    alert.setHeaderText("AES/ECB key length");
                    alert.setContentText("Length of key must be equal to 16 characters as its length is 128-bit.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = AES_ECB_PKCS5Padding_String.decrypt(cipherText, keyFromTextFieldForKey);
                    plainTextField.setText(plainText);
                }
            }
            break;
            case "Vernam":
            {
                //passwordFieldForKey.setDisable(false);
                boolean cipherTextChecker = Pattern.matches("[a-zA-Z]+", cipherTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Key Error");
                    alert.setHeaderText("Vernam Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.\nThe length of key should be equal to length of plain text.");
                    alert.showAndWait();
                }
                else if (!cipherTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vernam Cipher Text Error");
                    alert.setHeaderText("Vernam Cipher Text");
                    alert.setContentText("Cipher text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = Vernam.decrypt(cipherText, keyFromTextFieldForKey);
                    plainTextField.setText(plainText);
                }
            }
            break;
            case "Shift":
            {
                //passwordFieldForKey.setDisable(false);
                boolean cipherTextChecker = Pattern.matches("[a-zA-Z]+", cipherTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[0-9]+", keyFromTextFieldForKey);
                if (key)
                {
                    if (Integer.parseInt(keyFromTextFieldForKey) >= 0 && Integer.parseInt(keyFromTextFieldForKey) <= 25)
                        key = true;
                    else key = false;
                }
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shift Key Error");
                    alert.setHeaderText("Shift Cipher Key");
                    alert.setContentText("Key should consists of numbers only.\nIt should not have space in it.");
                    alert.showAndWait();
                }
                else if (!cipherTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shift Cipher Text Error");
                    alert.setHeaderText("Shift Cipher Text");
                    alert.setContentText("Cipher text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = Caesar.decrypt(cipherText, Integer.parseInt(keyFromTextFieldForKey));
                    plainTextField.setText(plainText);
                }
            }
            break;
            case "OneTimePad":
            {
                //passwordFieldForKey.setDisable(false);
                boolean cipherTextChecker = Pattern.matches("[a-zA-Z]+", cipherTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                if (key)
                {
                    if (keyFromTextFieldForKey.length() != cipherTextField.getText().length()) key = false;
                }
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("OneTimePad Key Error");
                    alert.setHeaderText("OneTimePad Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.");
                    alert.showAndWait();
                }
                else if (!cipherTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("OneTimePad Cipher Text Error");
                    alert.setHeaderText("OneTimePad Cipher Text");
                    alert.setContentText("OneTimePad text should consist of alphabets only.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = OneTimePad.decrypt(cipherText, keyFromTextFieldForKey);
                    plainTextField.setText(plainText);
                }
            }
            break;
            case "Vigenere":
            {
                //passwordFieldForKey.setDisable(false);
                boolean cipherTextChecker = Pattern.matches("[a-zA-Z]+", cipherTextField.getText());
                keyFromTextFieldForKey = passwordFieldForKey.getText();
                boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigenere Key Error");
                    alert.setHeaderText("Vigenere Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.\nThe length of key should be equal to length of plain text.");
                    alert.showAndWait();
                }
                else if (!cipherTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigenere Cipher Text Error");
                    alert.setHeaderText("Vigenere Cipher Text");
                    alert.setContentText("Cipher text should consist of alphabets only without space.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = VigenereCipher.decrypt(cipherText, keyFromTextFieldForKey);
                    plainTextField.setText(plainText);
                }
            }
            break;
            case "Mono Alphabetic":
            {
                boolean cipherTextChecker = Pattern.matches("[A-Z]+", cipherTextField.getText());
                //passwordFieldForKey.setDisable(true);
                //boolean key = Pattern.matches("[a-z]+", keyFromTextFieldForKey);
                //int lengthOfKey = keyFromTextFieldForKey.length();
                /*if (!key)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigenere Key Error");
                    alert.setHeaderText("Vigenere Cipher Key");
                    alert.setContentText("Key should consists of small letters only.\nIt should not have space in it.\nThe length of key should be equal to length of plain text.");
                    alert.showAndWait();
                }*/
                if (!cipherTextChecker)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mono Alphabetic Cipher Text Error");
                    alert.setHeaderText("Mono Alphabetic Cipher Text");
                    alert.setContentText("Cipher text should consist of Capital alphabets only without space.");
                    alert.showAndWait();
                }
                else
                {
                    String cipherText = cipherTextField.getText();
                    String plainText = MonoalphabeticCipher.decrypt(cipherText);
                    plainTextField.setText(plainText);
                }
            }
            break;
        }

        /*{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Key-File Pair");
            alert.setHeaderText("All or few of the KEY-FILE pairs are WRONG.");
            alert.setContentText("First " + (i - 1) + " file(s) are successfully decrypted.\nTry to decrypt the remaining files using proper common KEY.");
            alert.showAndWait();
        }*/
    }
}
