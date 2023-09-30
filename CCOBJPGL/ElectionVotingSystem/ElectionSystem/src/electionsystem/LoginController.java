package electionsystem;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtStudentID;
    
    @FXML
    private Hyperlink btnRedirectSignup;    
    
    Connection connect;
    PreparedStatement statement;
    ResultSet result;

    @FXML
    public void actLogin(ActionEvent event) throws SQLException, IOException {
        String studentID = txtStudentID.getText();
        String password = txtPassword.getText();

        if (studentID.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Student ID or Password is blank");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

                statement = connect.prepareStatement("SELECT * from users WHERE student_id = ? and password = ?");
                statement.setString(1, studentID);
                statement.setString(2, password);

                result = statement.executeQuery();

                if (result.next()) {
                    String userType = result.getString("user_type");
                    FXMLLoader loader;
                    if (userType.equals("admin")) {
                        // Load the admin dashboard
                        loader = new FXMLLoader(getClass().getResource("adminDashboard.fxml"));
                    } else {
                        // Load the user dashboard
                        loader = new FXMLLoader(getClass().getResource("userDashboard.fxml"));
                    }

                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect StudentID/Password");
                    txtStudentID.setText("");
                    txtPassword.setText("");
                    txtStudentID.requestFocus();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create account");
            stage.setScene(new Scene(root1));  
            stage.show();   
            
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
