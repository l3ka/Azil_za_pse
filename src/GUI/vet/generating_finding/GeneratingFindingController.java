package GUI.vet.generating_finding;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GeneratingFindingController {

    @FXML private TextArea descriptionTextArea;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void save() {
        if(checkDescription()) {
            displayAlertBox("Nalaz je uspješno generisan!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextArea.getText())) {
            displayAlertBox("Nije unesen opis!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
