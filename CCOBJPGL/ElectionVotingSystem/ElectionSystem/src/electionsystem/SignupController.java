package electionsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SignupController {

    @FXML
    private Button btnSignup;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtStudentID;

    Connection connect;
    PreparedStatement statement;

    @FXML
    public void actSignup(ActionEvent event) throws IOException {
        String studentID = txtStudentID.getText();
        String password = txtNewPassword.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();

        if (password.equals(txtConfirmPassword.getText())) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

                String query = "INSERT INTO users (student_id, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
                statement = connect.prepareStatement(query);
                statement.setString(1, studentID);
                statement.setString(2, password);
                statement.setString(3, firstName);
                statement.setString(4, lastName);
                statement.setString(5, email);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Signup Successful");

                    // Load the user dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();

                } else {
                    JOptionPane.showMessageDialog(null, "Signup Failed");
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");
            txtNewPassword.requestFocus();
        }
    }
}
