package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControllerOfHomePage
{
    @FXML
    private Button passwordManagerButton, checksumGeneratorButton;
    @FXML
    private Button protectionForStringsButton, protectionForFilesButton;

    public void openPasswordManager(ActionEvent event) throws IOException
    {

        Parent root = null;
        File file = new File("ImportantFile.txt");
        if(file.exists()) root = FXMLLoader.load(getClass().getResource("LoginDesign.fxml"));
        else root = FXMLLoader.load(getClass().getResource("RegisterDesign.fxml"));

        Scene scene = new Scene(root, 800, 600);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);

        primaryStage.setTitle("DATA ENCRYPTION");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void openChecksumGenerator(ActionEvent event) throws IOException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ChecksumGenerator.fxml"));
        primaryStage.setTitle("Checksum Generator");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void openProtectionForStrings(ActionEvent event) throws IOException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DesignForString.fxml"));
        primaryStage.setTitle("Encryption and Decryption for Strings");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void openProtectionForFiles(ActionEvent event) throws IOException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DesignForFiles.fxml"));
        primaryStage.setTitle("Encryption and Decryption for Files");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
