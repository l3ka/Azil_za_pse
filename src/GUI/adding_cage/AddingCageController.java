package GUI.adding_cage;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

public class AddingCageController {

    @FXML private TextField cageCapacityTextField;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void save() {
        if(checkCapacity()) {
            AzilUtilities.getDAOFactory().getCageDAO().insert(new CageDTO(0, Integer.valueOf(cageCapacityTextField.getText())));
            displayAlertBox("Kavez je uspješno dodat!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkCapacity() {
        if("".equals(cageCapacityTextField.getText())) {
            displayAlertBox("Niste unijeli kapacitet kaveza!");
            return false;
        } else if(!checkIsNumber(cageCapacityTextField.getText())) {
            displayAlertBox("Unos za polje kapacitet nije odgovarajući!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

    private boolean checkIsNumber(String number) {
        return number.matches("^[0-9]*$");
    }
}
