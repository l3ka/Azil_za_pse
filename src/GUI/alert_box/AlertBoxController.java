package GUI.alert_box;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        initButtonEvent();
    }

    private void initButtonEvent() {
        okButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                okButton.fire();
                e.consume();
            }
        });
    }

    public void ok() {
        stage.close();
    }

}
