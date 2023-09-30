package electionsystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;

public class UserDashboardController {

    @FXML
    private TableView<Election> tblVotingHome;

    @FXML
    private TableColumn<Election, String> tblVotingElectionName;
    
    @FXML
    private TableColumn<Election, String> tblVotingElectionStatus;

    private Connection connection;

    public void initialize() {
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set up the table columns
        tblVotingElectionName.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblVotingElectionStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load and display the active elections
        loadActiveElections();

        // Handle double-click event on table rows
        tblVotingHome.addEventHandler(MouseEvent.MOUSE_CLICKED, this::openVotingDashboard);
    }

    public void loadActiveElections() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM elections"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int electionId = resultSet.getInt("election_id");
                String electionName = resultSet.getString("title");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
                String status = getStatus(startDate, endDate);

                if (status.equals("Active")) {
                    Election election = new Election(electionId, electionName, status);
                    tblVotingHome.getItems().add(election);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openVotingDashboard(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Election selectedElection = tblVotingHome.getSelectionModel().getSelectedItem();
            if (selectedElection != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VotingDashboard.fxml"));
                    Parent root = loader.load();
                    VotingDashboardController votingDashboardController = loader.getController();
                    votingDashboardController.setElection(selectedElection.getElectionId());

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Voting Dashboard");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getStatus(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(startDate)) {
            return "Upcoming";
        } else if (currentDate.isAfter(endDate)) {
            return "Closed";
        } else {
            return "Active";
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class Election {
        private int electionId;
        private String title;
        private String status;

        public Election(int electionId, String title, String status) {
            this.electionId = electionId;
            this.title = title;
            this.status = status;
        }

        // Getter methods

        public int getElectionId() {
            return electionId;
        }

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }
    }
}
