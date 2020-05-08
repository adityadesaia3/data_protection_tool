package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class Login
{
    @FXML PasswordField passwordField3 = new PasswordField();

    public void Login1() throws Exception
    {
        String pass = passwordField3.getText();

        File file = new File("ImportantFile.txt");
        if (file.exists())
        {
            if (AES_ECB_PKCS5Padding.decrypt(file, pass))
            {
                AES_ECB_PKCS5Padding.encrypt(file, pass);
                new PasswordManager().getPasswordManager();
                new Design2().setLoginPassword(pass);
                new Display_Form().setLoginPassword(pass);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Information");
                alert.setHeaderText("Wrong Password");
                alert.setContentText("Log in not successful.");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Information");
            alert.setHeaderText("File doesn't exit.");
            alert.setContentText("Restart the program.");
            alert.showAndWait();
        }
    }

    public void forgetPassword(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to create a new account?");
        alert.setContentText("This is an irreversible process.\nAll your the current data in the database will be lost.\nAre you sure to move forward?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            File file = new File("ImportantFile.txt");
            file.delete();
            Stage stage2 = (Stage) passwordField3.getScene().getWindow();
            stage2.close();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Forget Password Information");
            alert.setHeaderText("Registration Page");
            alert.setContentText("You can now click on \"Password Manager\" to \"REGISTER\".");
            alert.showAndWait();
        }
    }
}
