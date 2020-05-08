package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordManager
{
    public void getPasswordManager() throws IOException
    {
	    Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Design.fxml"));
        primaryStage.setTitle("Password Manager");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
