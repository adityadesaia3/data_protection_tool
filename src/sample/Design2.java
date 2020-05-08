package sample;

import Connectivity.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class Design2
{

    @FXML private TextField email,name,header;
    @FXML private TextField password,password1;
    @FXML private Button b1,b2,b3;
    @FXML private TextArea ta;

    static String loginPassword = "";
    void setLoginPassword(String pass)
    {
        loginPassword = pass;
    }

    @FXML private void Email(ActionEvent ae)
    {


        name.setVisible(false);
        password1.setVisible(false);
        b2.setVisible(false);

        header.setVisible(false);
        ta.setVisible(false);
        b3.setVisible(false);

        email.setVisible(true);
        password.setVisible(true);
        b1.setVisible(true);
    }

    @FXML private void Website(ActionEvent ae){

        email.setVisible(false);
        password.setVisible(false);
        b1.setVisible(false);

        header.setVisible(false);
        ta.setVisible(false);
        b3.setVisible(false);

        name.setVisible(true);
        password1.setVisible(true);
        b2.setVisible(true);

    }

    @FXML private void Notes(ActionEvent ae){

        email.setVisible(false);
        password.setVisible(false);
        b1.setVisible(false);

        name.setVisible(false);
        password1.setVisible(false);
        b2.setVisible(false);

        header.setVisible(true);
        ta.setVisible(true);
        b3.setVisible(true);

    }

    public void insert_email(ActionEvent a)
    {
        try
        {
            Connect c = new Connect();
            Connection conn = c.getconnection();
            Statement stmt = conn.createStatement();

            // Inserting
            String emailid=email.getText();
            String email_password=password.getText();
            if(emailid.equals("") || email_password.equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Enter Details!");

                alert.showAndWait();

            }
            else {
                String str = "INSERT INTO EMAIL VALUES ('" + AES_ECB_PKCS5Padding_String.encrypt(email.getText(), loginPassword) + "' , '" + AES_ECB_PKCS5Padding_String.encrypt(password.getText(), loginPassword) + "')";

                stmt.executeUpdate(str);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Inserted!");

                alert.showAndWait();
                email.setText("");
                password.setText("");
                System.out.println("Record Inserted");
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    public void insert_website(ActionEvent a){
            try {
                Connect c = new Connect();
                Connection conn = c.getconnection();
                Statement stmt = conn.createStatement();


                // Insert

                String website_name=name.getText();
                String web_pss=password1.getText();

                if(website_name.equals("") || web_pss.equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter Details!");

                    alert.showAndWait();

                }
                else {


                    String str = "INSERT INTO WEBSITE VALUES ('" + AES_ECB_PKCS5Padding_String.encrypt(name.getText(), loginPassword) + "' , '" + AES_ECB_PKCS5Padding_String.encrypt(password1.getText(), loginPassword) + "')";

                    stmt.executeUpdate(str);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Record Inserted!");

                    alert.showAndWait();

                    name.setText("");
                    password1.setText("");

                    System.out.println("Record Inserted");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
    }
    public void insert_notes(ActionEvent a){
            try {
                Connect c = new Connect();
                Connection conn = c.getconnection();
                Statement stmt = conn.createStatement();

                String header1=header.getText();
                String note=ta.getText();

                if(header1.equals("") || note.equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter Details!");

                    alert.showAndWait();
                }
                else
                {

                    String str = "INSERT INTO NOTE VALUES ('" + AES_ECB_PKCS5Padding_String.encrypt(header.getText(), loginPassword) + "','" + AES_ECB_PKCS5Padding_String.encrypt(ta.getText(), loginPassword) + "')";

                    stmt.executeUpdate(str);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Record Inserted!");

                    alert.showAndWait();

                    ta.setText("");
                    header.setText("");
                    System.out.println("Record Inserted");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
    }

    public void Display(ActionEvent ae){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));
                Parent root = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setTitle("Password Manager");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(Exception e){
                System.out.println("Can't load new window");
            }
    }
}
