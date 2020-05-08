package sample;

import Connectivity.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Display_Form
{
    static String loginPassword = "";
    void setLoginPassword(String pass)
    {
        loginPassword = pass;
    }

    Connection connection = null;
    Statement statement = null;
    Vector<Email> emailSet = new Vector<Email>();
    int emailSetIndex = 0;
    String emailBeforeUpdate,websiteBeforeUpdate,notesBeforeUpadte;

    Vector<Website>webSet = new Vector<Website>();
    int websiteIndex = 0;

    Vector<Notes>notesSet = new Vector<Notes>();
    int NotesIndex = 0;

    @FXML private TextField email,password;
    @FXML private Button n1,u1,d1,s1;


    @FXML private TextField name,password1;
    @FXML private Button n2,u2,d2,s2;

    @FXML private TextField header;
    @FXML private TextArea ta;
    @FXML private Button n3,u3,d3,s3;

    ResultSet resultSet = null;
    ResultSet resultSet_website = null;
    ResultSet resultSet_Notes = null;
    private void updateResultSet()
    {
        emailSet = new Vector<>();
        try {
            Connect connect = new Connect();
            connection = connect.getconnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from EMAIL");

            while (resultSet.next())
            {
                String emailid = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(1), loginPassword);
                String epass = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(2), loginPassword);

                emailSet.add(new Email(emailid, epass));
            }
        }
        catch (Exception e) { e.printStackTrace(); }

    }



    private void updateResultSet_Website()
    {
        webSet = new Vector<>();
        try {
            Connect connect = new Connect();
            connection = connect.getconnection();
            statement = connection.createStatement();
            resultSet_website = statement.executeQuery("select * from WEBSITE");

            while (resultSet_website.next())
            {
                String website = AES_ECB_PKCS5Padding_String.decrypt(resultSet_website.getString(1), loginPassword);
                String wpass = AES_ECB_PKCS5Padding_String.decrypt(resultSet_website.getString(2), loginPassword);

                webSet.add(new Website(website, wpass));
            }
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    private void updateResultSet_Notes()
    {
        notesSet = new Vector<>();
        try {
            Connect connect = new Connect();
            connection = connect.getconnection();
            statement = connection.createStatement();
            resultSet_Notes = statement.executeQuery("select * from NOTE");

            while (resultSet_Notes.next())
            {
                String header = AES_ECB_PKCS5Padding_String.decrypt(resultSet_Notes.getString(1), loginPassword);
                String notes = AES_ECB_PKCS5Padding_String.decrypt(resultSet_Notes.getString(2), loginPassword);
                notesSet.add(new Notes(header,notes));
            }
        }
        catch (Exception e) { e.printStackTrace(); }

    }


    public void  Display_Email(ActionEvent ae){


        name.setVisible(false);
        password1.setVisible(false);
        n2.setVisible(false);
        u2.setVisible(false);
        d2.setVisible(false);
        s2.setVisible(false);

        header.setVisible(false);
        ta.setVisible(false);
        n3.setVisible(false);
        u3.setVisible(false);
        d3.setVisible(false);
        s3.setVisible(false);

        email.setVisible(true);
        password.setVisible(true);
        n1.setVisible(true);
        u1.setVisible(true);
        d1.setVisible(true);
        s1.setVisible(true);

        updateResultSet();
    }

    public void  Display_Website(ActionEvent ae){
        email.setVisible(false);
        password.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(false);
        d1.setVisible(false);
        s1.setVisible(false);

        header.setVisible(false);
        ta.setVisible(false);
        n3.setVisible(false);
        u3.setVisible(false);
        d3.setVisible(false);
        s3.setVisible(false);

        name.setVisible(true);
        password1.setVisible(true);
        n2.setVisible(true);
        u2.setVisible(true);
        d2.setVisible(true);
        s2.setVisible(true);

        updateResultSet_Website();

    }

    public void  Display_Notes(ActionEvent ae){
        email.setVisible(false);
        password.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(false);
        d1.setVisible(false);
        s1.setVisible(false);


        name.setVisible(false);
        password1.setVisible(false);
        n2.setVisible(false);
        u2.setVisible(false);
        d2.setVisible(false);
        s2.setVisible(false);

        header.setVisible(true);
        ta.setVisible(true);
        n3.setVisible(true);
        u3.setVisible(true);
        d3.setVisible(true);
        s3.setVisible(true);


        updateResultSet_Notes();
    }

    public void next_button(ActionEvent event)
    {
        emailBeforeUpdate = emailSet.elementAt(emailSetIndex).emailid;
        email.setText(emailSet.elementAt(emailSetIndex).emailid);
        password.setText(emailSet.elementAt(emailSetIndex).epass);
        emailSetIndex++;
        if (emailSetIndex >= emailSet.size())
            emailSetIndex = 0;
    }

    public void next_button_Website(ActionEvent event)
    {
        websiteBeforeUpdate = webSet.elementAt(websiteIndex).website;
        name.setText(webSet.elementAt(websiteIndex).website);
        password1.setText(webSet.elementAt(websiteIndex).wpass);
        websiteIndex++;
        if (websiteIndex >= webSet.size())
            websiteIndex = 0;
    }

    public void next_button_Notes(ActionEvent event)
    {
        notesBeforeUpadte = notesSet.elementAt(NotesIndex).header;
        header.setText(notesSet.elementAt(NotesIndex).header);
        ta.setText(notesSet.elementAt(NotesIndex).notes);
        NotesIndex++;
        if (NotesIndex >= notesSet.size())
            NotesIndex = 0;
    }


    public void Update_Email(ActionEvent ae)
    {
        String emailid = email.getText();
        String epass = password.getText();

        if(emailid.equals("") || epass.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Updated!");
            alert.showAndWait();
        }
        else {
            try {
                statement.executeUpdate("update EMAIL set email = '" + AES_ECB_PKCS5Padding_String.encrypt(emailid, loginPassword) + "', password = '" + AES_ECB_PKCS5Padding_String.encrypt(epass, loginPassword) + "' where email = '" + AES_ECB_PKCS5Padding_String.encrypt(emailBeforeUpdate, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Updated!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            updateResultSet();
        }
    }


    public void Update_Website(ActionEvent ae)
    {
        String website = name.getText();
        String wpass = password1.getText();

        if(website.equals("") || wpass.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Updated!");

            alert.showAndWait();

        }
        else {
            try {
                statement.executeUpdate("update WEBSITE set name = '" + AES_ECB_PKCS5Padding_String.encrypt(website, loginPassword) + "', password = '" + AES_ECB_PKCS5Padding_String.encrypt(wpass, loginPassword) + "' where name = '" + AES_ECB_PKCS5Padding_String.encrypt(websiteBeforeUpdate, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Updated!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            updateResultSet_Website();
        }
    }


    public void Update_Notes(ActionEvent ae)
    {
        String  header1 = header.getText();
        String notes = ta.getText();

        if(header1.equals("") || notes.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Updated!");

            alert.showAndWait();

        }
        else {
            try {
                statement.executeUpdate("update NOTE set header = '" + AES_ECB_PKCS5Padding_String.encrypt(header1, loginPassword) + "' , note = '" + AES_ECB_PKCS5Padding_String.encrypt(notes, loginPassword) + "' where header = '" + AES_ECB_PKCS5Padding_String.encrypt(notesBeforeUpadte, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Updated!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            updateResultSet_Notes();
        }
    }


    public void delete_Email(ActionEvent ae)
    {
        String emailid = email.getText();
        String epass = password.getText();

        //System.out.println(emailid);
        if(emailid.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Deleted!");

            alert.showAndWait();

        }
        else {
            try {
                statement.executeUpdate("delete from EMAIL where email = '" + AES_ECB_PKCS5Padding_String.encrypt(emailid, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Deleted!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (emailSetIndex > 0) emailSetIndex--;
            else
                emailSetIndex = 0;
            email.setText("");
            password.setText("");
            updateResultSet();
        }
    }

    public void delete_Website(ActionEvent ae)
    {
        String website = name.getText();

       // System.out.println(emailid);
        if(website.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Deleted!");

            alert.showAndWait();

        }
        else {

            try {
                statement.executeUpdate("delete from WEBSITE where name = '" + AES_ECB_PKCS5Padding_String.encrypt(website, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Deleted!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (websiteIndex > 0) websiteIndex--;
            else
                websiteIndex = 0;
            name.setText("");
            password1.setText("");
            updateResultSet_Website();
        }
    }

    public void delete_Notes(ActionEvent ae)
    {
        String notes = ta.getText();

        if(notes.equals("")){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record Not Deleted!");

            alert.showAndWait();
        }
        else {
            try {
                statement.executeUpdate("delete from NOTE where note = '" + AES_ECB_PKCS5Padding_String.encrypt(notes, loginPassword) + "'");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Record Deleted!");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (NotesIndex > 0) NotesIndex--;
            else
                NotesIndex = 0;
            ta.setText("");
            header.setText("");
            updateResultSet_Notes();
        }
    }

    public void search_email(ActionEvent ae){
        String email_id=email.getText();
        try{
            if(email_id.equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Enter Details!");

                alert.showAndWait();
            }
            else{
                statement = connection.createStatement();
                String encrypted_email = AES_ECB_PKCS5Padding_String.encrypt(email_id, loginPassword);

                resultSet  = statement.executeQuery("select * from EMAIL where email='"+encrypted_email+"'");
                System.out.println(resultSet.getFetchSize());
                System.out.println(email_id);
                System.out.println(encrypted_email);

                while(resultSet.next()){
                    String Decrypted_Email = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(1), loginPassword);
                    String Decrypted_pass = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(2), loginPassword);
                    System.out.println(Decrypted_pass);
                    email.setText(Decrypted_Email);
                    password.setText(Decrypted_pass);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void search_website(ActionEvent ae){
        String web=name.getText();
        try{
            if(web.equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Enter Details!");

                alert.showAndWait();
            }
            else{
                statement = connection.createStatement();
                String encrypted_web = AES_ECB_PKCS5Padding_String.encrypt(web, loginPassword);

                resultSet  = statement.executeQuery("select * from WEBSITE where name='"+encrypted_web+"'");
                System.out.println(resultSet.getFetchSize());
              //  System.out.println(email_id);
            //    System.out.println(encrypted_email);

                while(resultSet.next()){
                    String Decrypted_web = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(1), loginPassword);
                    String Decrypted_pass = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(2), loginPassword);
                    System.out.println(Decrypted_pass);
                    name.setText(Decrypted_web);
                    password1.setText(Decrypted_pass);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void search_note(ActionEvent ae){
        String header1=header.getText();
        try{
            if(header1.equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Enter Details!");

                alert.showAndWait();
            }
            else{
                statement = connection.createStatement();
                String encrypted_header = AES_ECB_PKCS5Padding_String.encrypt(header1, loginPassword);

                resultSet  = statement.executeQuery("select * from NOTE where header='"+encrypted_header+"'");
                System.out.println(resultSet.getFetchSize());


                while(resultSet.next()){
                    String Decrypted_header = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(1), loginPassword);
                    String Decrypted_note = AES_ECB_PKCS5Padding_String.decrypt(resultSet.getString(2), loginPassword);
                    header.setText(Decrypted_header);
                    ta.setText(Decrypted_note);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }




}
