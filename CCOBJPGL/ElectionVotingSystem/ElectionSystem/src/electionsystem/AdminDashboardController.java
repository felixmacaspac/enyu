package electionsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import electionsystem.CandidateManagementController;

public class AdminDashboardController {

    @FXML
    private Button btnAddElection;

    @FXML
    private Button btnDeleteElection;

    @FXML
    private Button btnEditElection;

    @FXML
    private TableView<Election> tblElection;

    @FXML
    private TableColumn<Election, Integer> tblElectionID;

    @FXML
    private TableColumn<Election, String> tblElectionName;

    @FXML
    private TableColumn<Election, String> tblElectionStatus;

    @FXML
    private TextField txtElectionName;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    @FXML
    public void actAddElection(ActionEvent event) {
        String electionName = txtElectionName.getText();
        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

            statement = connect.prepareStatement("INSERT INTO elections (title, start_date, end_date) VALUES (?, ?, ?)");
            statement.setString(1, electionName);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Election added successfully");
                txtElectionName.setText("");
                dpStartDate.setValue(null);
                dpEndDate.setValue(null);
                loadElections();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add election");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void actDeleteElection(ActionEvent event) {
        Election selectedElection = tblElection.getSelectionModel().getSelectedItem();
        if (selectedElection == null) {
            JOptionPane.showMessageDialog(null, "No election selected");
            return;
        }

        int electionId = selectedElection.getId();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

            statement = connect.prepareStatement("DELETE FROM elections WHERE election_id = ?");
            statement.setInt(1, electionId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Election deleted successfully");
                loadElections();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete election");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void actEditElection(ActionEvent event) {
        Election selectedElection = tblElection.getSelectionModel().getSelectedItem();
        if (selectedElection != null) {
            int electionId = selectedElection.getId();
            String electionName = txtElectionName.getText();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

                statement = connect.prepareStatement("UPDATE elections SET title = ?, start_date = ?, end_date = ? WHERE election_id = ?");
                statement.setString(1, electionName);
                statement.setDate(2, startDate != null ? Date.valueOf(startDate) : null);
                statement.setDate(3, endDate != null ? Date.valueOf(endDate) : null);
                statement.setInt(4, electionId);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Election updated successfully");
                    txtElectionName.setText("");
                    dpStartDate.setValue(null);
                    dpEndDate.setValue(null);
                    loadElections();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update election");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void initialize() {
        tblElectionID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tblElectionName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tblElectionStatus.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                calculateStatus(cellData.getValue().getStartDate(), cellData.getValue().getEndDate())));

        tblElection.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Election selectedElection = tblElection.getSelectionModel().getSelectedItem();
                txtElectionName.setText(selectedElection.getName());
                dpStartDate.setValue(selectedElection.getStartDate());
                dpEndDate.setValue(selectedElection.getEndDate());
            }
        });
        
        tblElection.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detect double-click
                Election selectedElection = tblElection.getSelectionModel().getSelectedItem();
                if (selectedElection != null) {
                    int electionId = selectedElection.getId();
                    openCandidateManagement(electionId);
                }
            }
        });
        

        loadElections();
    }
    
    private void openCandidateManagement(int electionId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CandidateManagement.fxml"));
            Parent root = loader.load();
            CandidateManagementController candidateManagementController = loader.getController();
            candidateManagementController.setElectionId(electionId);
            Scene candidateManagementScene = new Scene(root);
            Stage currentStage = (Stage) btnAddElection.getScene().getWindow();
            currentStage.setScene(candidateManagementScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    private void loadElections() {
        tblElection.getItems().clear();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

            statement = connect.prepareStatement("SELECT * FROM elections");
            result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("election_id");
                String name = result.getString("title");
                LocalDate startDate = result.getDate("start_date").toLocalDate();
                LocalDate endDate = result.getDate("end_date").toLocalDate();
                Election election = new Election(id, name, startDate, endDate);
                tblElection.getItems().add(election);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String calculateStatus(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(startDate)) {
            return "Upcoming";
        } else if (currentDate.isAfter(endDate)) {
            return "Closed";
        } else {
            return "Active";
        }
    }

    public static class Election {
        private final SimpleIntegerProperty id;
        private final StringProperty name;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Election(int id, String name, LocalDate startDate, LocalDate endDate) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }
}
