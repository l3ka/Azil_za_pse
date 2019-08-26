package GUI.alert_box;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertBoxController {
    @FXML
    private Label label;
    @FXML private Button okButton;

    @FXML
    public void initialize() {
        label.setText(AlertBoxForm.text);
    }

    public void ok() {
        Stage prozor = (Stage) okButton.getScene().getWindow();
        prozor.close();
    }
}
