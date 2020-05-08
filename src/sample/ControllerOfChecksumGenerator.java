package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerOfChecksumGenerator implements Initializable
{
    private String algorithmName;
    private File filePathName;
    private String checksum;
    private String originalChecksumOfFile;

    @FXML
    private ComboBox<String> comboBoxForAlgorithmNames;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textFieldForChooseFile;
    @FXML
    private TextField textFieldForCheckIntegrity;
    @FXML
    private TextField textFieldForString;
    @FXML
    private AnchorPane mainAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<String> algorithmNameList;
        algorithmNameList = FXCollections.observableArrayList("MD5", "SHA-1", "SHA-224", "SHA-256",
                "SHA-384", "SHA-512", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512");
        comboBoxForAlgorithmNames.setItems(algorithmNameList);
    }

    public  void selectAlgorithmName(ActionEvent event)
    {
        algorithmName = comboBoxForAlgorithmNames.getValue();
        mainAnchorPane.setDisable(false);
    }
    public  void pressChooseFile(ActionEvent event)
    {
        filePathName = HashGenerator.chooseFile();
        textFieldForChooseFile.setText(String.valueOf(filePathName));
    }
    public  void pressCheckIntegrity(ActionEvent event)
    {
        originalChecksumOfFile = textFieldForCheckIntegrity.getText();
        // Modification needed here
        if (checksum != null && !originalChecksumOfFile.equals(""))
        {
            if (checksum.equalsIgnoreCase(originalChecksumOfFile)) {
            /*Dialog<String> dp = new Dialog();
            dp.setContentText("Integrity OK.\nRecommended to use.");
            dp.setTitle("Integrity Check");
            dp.show();
            dp.close();*/
                writeToTextArea("File");
                textArea.appendText("\nStatus\t\t: " + "Integrity OK. Recommended to use.");
            } else {
            /*Dialog<String> dp = new Dialog();
            dp.setContentText("Integrity Compromised.\nNot Recommended to use.");
            dp.setTitle("Integrity Check")
            dp.show();
            dp.close();*/
                writeToTextArea("File");
                textArea.appendText("\nStatus\t\t: " + "Integrity COMPROMISED. Not Recommended to use.");
            }
        }
    }
    public  void pressGenerateChecksumForFile(ActionEvent event)
    {
        if (filePathName != null && algorithmName != null)
        {
            checksum = HashGenerator.generateChecksum(filePathName, algorithmName);
            writeToTextArea("File");
        }
    }
    public  void pressGenerateChecksumForString(ActionEvent event)
    {
        if (algorithmName != null)
        {
            checksum = HashGenerator.generateChecksum(textFieldForString.getText().getBytes(), algorithmName);
            writeToTextArea("String");
        }
    }
    private void writeToTextArea(String type)
    {
        if (type.equals("String"))
        {
            textArea.setText("\nAlgorithm\t: " + algorithmName);
            textArea.appendText("\nChecksum\t: " + checksum);
            textArea.appendText("\nString length\t: " + textFieldForString.getText().length());
        }
        else if (type.equals("File"))
        {
            textArea.setText("\nAlgorithm\t: " + algorithmName);
            textArea.appendText("\nChecksum\t: " + checksum);
            textArea.appendText("\nFile Path\t\t: " + filePathName);
            textArea.appendText("\nFile Name\t: " + filePathName.getName());
        }
    }
}