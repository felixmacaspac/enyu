package electionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidateManagementController {
    @FXML
    private Button btnAddCandidate;

    @FXML
    private Button btnDeleteCandidate;

    @FXML
    private Button btnEditCandidate;

    @FXML
    private TableView<Candidate> tblCandidate;

    @FXML
    private TextField txtCandidateName;

    @FXML
    private ComboBox<String> cbxCandidatePosition;

    @FXML
    private TableColumn<Candidate, String> tblCandidateName;

    @FXML
    private TableColumn<Candidate, String> tblCandidatePosition;

    private int electionId;
    private ObservableList<Candidate> candidates;

    private Connection connection;

    public void setElectionId(int electionId) {
        this.electionId = electionId;
        loadCandidates();
    }

    @FXML
    public void actAddCandidate(ActionEvent event) {
        String candidateName = txtCandidateName.getText();
        String candidatePosition = cbxCandidatePosition.getValue();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO candidates (election_id, name, position) VALUES (?, ?, ?)"
            );
            statement.setInt(1, electionId);
            statement.setString(2, candidateName);
            statement.setString(3, candidatePosition);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                loadCandidates(); 
                txtCandidateName.setText("");
                cbxCandidatePosition.setValue(null);
            } else {
                System.out.println("Failed to add candidate");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void actDeleteCandidate(ActionEvent event) {
        Candidate selectedCandidate = tblCandidate.getSelectionModel().getSelectedItem();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM candidates WHERE candidate_id = ?"
            );
            statement.setInt(1, selectedCandidate.getCandidateId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                loadCandidates(); 
            } else {
                System.out.println("Failed to delete candidate");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void actEditCandidate(ActionEvent event) {
        Candidate selectedCandidate = tblCandidate.getSelectionModel().getSelectedItem();

        String candidateName = txtCandidateName.getText();
        String candidatePosition = cbxCandidatePosition.getValue();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE candidates SET name = ?, position = ? WHERE candidate_id = ?"
            );
            statement.setString(1, candidateName);
            statement.setString(2, candidatePosition);
            statement.setInt(3, selectedCandidate.getCandidateId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                loadCandidates(); 
                txtCandidateName.setText("");
                cbxCandidatePosition.setValue(null);
            } else {
                System.out.println("Failed to edit candidate");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCandidates() {
        candidates = FXCollections.observableArrayList();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM candidates WHERE election_id = ?"
            );
            statement.setInt(1, electionId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int candidateId = resultSet.getInt("candidate_id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");

                Candidate candidate = new Candidate(candidateId, name, position);
                candidates.add(candidate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblCandidate.setItems(candidates);
    }

    public void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblCandidateName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tblCandidatePosition.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        String[] positionChoices = {"President", "Vice President", "Secretary"};
        ObservableList<String> positionOptions = FXCollections.observableArrayList(positionChoices);
        cbxCandidatePosition.setItems(positionOptions);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidateManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class Candidate {
        private int candidateId;
        private String name;
        private String position;

        public Candidate(int candidateId, String name, String position) {
            this.candidateId = candidateId;
            this.name = name;
            this.position = position;
        }

        public int getCandidateId() {
            return candidateId;
        }

        public void setCandidateId(int candidateId) {
            this.candidateId = candidateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public StringProperty nameProperty() {
            return new SimpleStringProperty(name);
        }

        public StringProperty positionProperty() {
            return new SimpleStringProperty(position);
        }
    }
}