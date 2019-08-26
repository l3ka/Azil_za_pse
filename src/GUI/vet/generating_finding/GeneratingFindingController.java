package GUI.vet.generating_finding;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GeneratingFindingController {

    @FXML private TextArea descriptionTextArea;

    @FXML
    private void initialize() {

    }

    public void save() {
        if(checkDescription()) {
            try {
                new AlertBoxForm("Nalaz je uspješno generisan!").display();
            } catch(Exception exception) {

            }
            descriptionTextArea.setText("");
        }
    }

    public void quit() {
        Stage stage = (Stage) descriptionTextArea.getScene().getWindow();
        stage.close();
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextArea.getText())) {
            try {
                new AlertBoxForm("Nije unesen opis!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }
}
