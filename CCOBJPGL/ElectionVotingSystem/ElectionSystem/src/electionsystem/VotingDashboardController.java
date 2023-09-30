package electionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VotingDashboardController {

    @FXML
    private Button btnSubmitVote;

    @FXML
    private ComboBox<Candidate> cbxPresidentCandidates;

    @FXML
    private ComboBox<Candidate> cbxSecretaryCandidates;

    @FXML
    private ComboBox<Candidate> cbxViceCandidates;

    @FXML
    void actSubmitVote(ActionEvent event) {
        Candidate presidentCandidate = cbxPresidentCandidates.getValue();
        Candidate secretaryCandidate = cbxSecretaryCandidates.getValue();
        Candidate viceCandidate = cbxViceCandidates.getValue();
    }

    private ObservableList<Candidate> fetchCandidatesForElectionAndPosition(int electionId, String position) {
        ObservableList<Candidate> candidates = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electiondb", "root", "");

            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM candidates WHERE election_id = ? AND position = ?"
            );
            statement.setInt(1, electionId);
            statement.setString(2, position);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int candidateId = resultSet.getInt("candidate_id");
                String name = resultSet.getString("name");
                String candidatePosition = resultSet.getString("position");

                Candidate candidate = new Candidate(candidateId, name, candidatePosition);
                candidates.add(candidate);
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(VotingDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return candidates;
    }

    void setElection(int electionId) {
        ObservableList<Candidate> presidentCandidates = fetchCandidatesForElectionAndPosition(electionId, "President");
        ObservableList<Candidate> secretaryCandidates = fetchCandidatesForElectionAndPosition(electionId, "Secretary");
        ObservableList<Candidate> viceCandidates = fetchCandidatesForElectionAndPosition(electionId, "Vice President");

        cbxPresidentCandidates.setItems(presidentCandidates);
        cbxSecretaryCandidates.setItems(secretaryCandidates);
        cbxViceCandidates.setItems(viceCandidates);
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

        @Override
        public String toString() {
            return name;
        }
    }
}
