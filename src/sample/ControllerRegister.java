package sample;


import Connectivity.NewDatabaseCreation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


import java.io.*;
import java.nio.Buffer;


public class ControllerRegister {
    @FXML PasswordField passwordField1 = new PasswordField();
    @FXML PasswordField passwordField2 = new PasswordField();
    @FXML Button b = new Button();
    @FXML Label l1 = new Label();
    @FXML Label l2 = new Label();

    private void goToLogin()
    {
        try
        {
            Stage stage2 = (Stage) b.getScene().getWindow();
            // do what you have to do
            stage2.close();

            FXMLLoader fxmlloder =new FXMLLoader(getClass().getResource("LoginDesign.fxml"));
            Parent root =(Parent) fxmlloder.load();
            Stage stage=new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));

            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Can't load new window");
        }
    }
    private void writeToTheFile(File file)
    {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try
        {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("This is the password file.");
            bufferedWriter.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void Register(ActionEvent event) throws Exception
    {
        String pass = passwordField1.getText();
        if (pass.length() == 16)
        {
            String c_pass = passwordField2.getText();
            if (c_pass.length() == 16)
            {
                if(pass.equals(""))
                {
                    l1.setText("Enter Password");
                    if(c_pass.equals(""))
                    {
                        l2.setText("Enter Password");
                    }
                }
                else if(!pass.equals("") && c_pass.equals(""))
                {
                    l1.setText("");
                    l2.setText("Enter Password");
                }
                else if(pass.equals(c_pass))
                {
                    File file = new File("ImportantFile.txt");
                    file.createNewFile();
                    writeToTheFile(file);
                    AES_ECB_PKCS5Padding.encrypt(file, c_pass);
                    // Creates New Database by deleting the previous one.
                    NewDatabaseCreation.createNewDatabase();
                    //System.out.println(file.setReadOnly());
                    goToLogin();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Passwords checking");
                    alert.setHeaderText("Passwords does not match");
                    alert.setContentText("Both passwords should be equal.");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Length");
                alert.setHeaderText("Password Length of Confirm Password");
                alert.setContentText("Enter 128 bit key i.e. 16 characters long for confirm password.");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Length");
            alert.setHeaderText("AES Password");
            alert.setContentText("Enter 128 bit key i.e. 16 characters long.");
            alert.showAndWait();
        }
    }
}
