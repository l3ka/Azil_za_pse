package GUI.decide_box;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DecideBoxController {

    @FXML
    private Label messageLabel;
    private Stage stage;
    private boolean decision = false;

    public void initialize(Stage stage, String message) {
        this.stage = stage;
         messageLabel.setText(message);
    }

    public void yesClicked() {
        stage.close();
        decision = true;
    }

    public void noClicked() {
        this.stage.close();
    }

    public boolean getDecision() {
        return this.decision;
    }
}
