package electionsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Signup extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            
            primaryStage.setTitle("Signup");
            primaryStage.setScene(new Scene(loginPage));
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
