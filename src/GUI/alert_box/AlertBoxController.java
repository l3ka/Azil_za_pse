package GUI.alert_box;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertBoxController {
    @FXML
    private Label label;
    @FXML
    private Button okButton;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        label.setText(AlertBoxForm.text);
    }

    public void ok() {
        stage.close();
    }
}
