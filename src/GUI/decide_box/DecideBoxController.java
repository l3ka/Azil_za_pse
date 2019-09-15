package GUI.decide_box;

import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class DecideBoxController {

    @FXML
    private Label messageLabel;

    private Stage stage;
    private boolean decision = false;

    public void initialize(Stage stage, String message) {
        try {
            this.stage = stage;
            messageLabel.setText(message);
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DecideBoxController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void yesClicked() {
        try {
            stage.close();
            decision = true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DecideBoxController - yesClicked", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void noClicked() {
        try {
            this.stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DecideBoxController - noClicked", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public boolean getDecision() {
            return this.decision;
    }

}
