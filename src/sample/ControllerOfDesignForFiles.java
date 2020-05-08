package sample;

/*
1. Why getBytes doesn't output the same value as like Base64?
2. ECB vs CBC
3. StandardCharsets.UTF-8
4. PKCS5
5. SecretKeySpec
6. Base64, getEncoder, getDecoder, encode, decode

7. Improvement
    Text Input
    Use of CBC, Initialization Vector
    Upgrading from 128-bit key to 256-bit key
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerOfDesignForFiles implements Initializable
{
    private String algorithmName = null;
    private List<File> listOFFilePathNames = null;
    private String keyFromTextFieldForKey = null;

    @FXML
    private ComboBox<String> comboBoxForAlgorithmNames;
    @FXML
    private TextField passwordFieldForKey;
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
    @FXML
    private ListView listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<String> algorithmNameList;
        algorithmNameList = FXCollections.observableArrayList("AES/ECB");
        comboBoxForAlgorithmNames.setItems(algorithmNameList);
    }

    public  void selectAlgorithmName(ActionEvent event)
    {
        algorithmName = comboBoxForAlgorithmNames.getValue();
        encryptDecryptAnchorPane.setDisable(false);
    }
    public void choosingTheRadioButton(ActionEvent event)
    {
        if(encryptRadioButton.isSelected())
        {
            encryptButton.setDisable(false);
            decryptButton.setDisable(true);
        }
        else if (decryptRadioButton.isSelected())
        {
            encryptButton.setDisable(true);
            decryptButton.setDisable(false);
        }
    }
    public  void pressChooseFile(ActionEvent event)
    {
        listOFFilePathNames = HashGenerator.chooseMultipleFiles();
        listView.getItems().removeAll();
        listView.getItems().clear();
        for (File filePathName : listOFFilePathNames)
        {
            listView.getItems().add(filePathName);
        }
    }
    public void encryptTheFiles(ActionEvent event)
    {
        if (algorithmName.equals("AES/ECB"))
        {
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
                for (File filePathName : listOFFilePathNames)
                {
                    if (filePathName != null && !keyFromTextFieldForKey.equals(""))
                    {
                        //keyFromTextFieldForKey = HashGenerator.generateChecksum(keyFromTextFieldForKey.getBytes(), "SHA-512");
                        //keyFromTextFieldForKey = keyFromTextFieldForKey.substring(keyFromTextFieldForKey.length() - 16);
                        AES_ECB_PKCS5Padding.encrypt(filePathName, keyFromTextFieldForKey);
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Encryption Information");
                alert.setHeaderText("Successfully Encrypted!");
                alert.setContentText("Given files are successfully encrypted.");
                alert.showAndWait();
            }
        }
    }
    public void decryptTheFile(ActionEvent event)
    {
        if (algorithmName.equals("AES/ECB"))
        {
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
                boolean isAllgood = true;
                int i = 0;
                for (i = 0; i < listOFFilePathNames.size() && isAllgood; i++)
                {
                    if (listOFFilePathNames.get(i) != null && !keyFromTextFieldForKey.equals(""))
                    {
                        //keyFromTextFieldForKey = HashGenerator.generateChecksum(keyFromTextFieldForKey.getBytes(), "SHA-512");
                        //keyFromTextFieldForKey = keyFromTextFieldForKey.substring(keyFromTextFieldForKey.length() - 16);
                        isAllgood = AES_ECB_PKCS5Padding.decrypt(listOFFilePathNames.get(i), keyFromTextFieldForKey);
                    }
                }
                if (isAllgood)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Decryption Information");
                    alert.setHeaderText("Successfully Decrypted");
                    alert.setContentText("Given files are successfully Decrypted.");
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Key-File Pair");
                    alert.setHeaderText("All or few of the KEY-FILE pairs are WRONG.");
                    alert.setContentText("First " + (i - 1) + " file(s) are successfully decrypted.\nTry to decrypt the remaining files using proper common KEY.");
                    alert.showAndWait();
                }
            }

        }

    }
}
