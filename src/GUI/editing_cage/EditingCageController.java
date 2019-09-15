package GUI.editing_cage;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

public class EditingCageController {

    @FXML
    private TextField cageNameTextField;
    @FXML
    private TextField cageCapacityTextField;

    private Stage stage;
    private CageDTO cage;

    public void initialize(Stage stage, CageDTO cage) {
        this.stage = stage;
        this.cage = cage;

        cageNameTextField.setText(cage.getName());
        cageCapacityTextField.setText("" + cage.getCapacity());
    }

    public void save() {
        if(checkName() && checkCapacity()) {
            if(AzilUtilities.getDAOFactory().getCageDAO().update(cage)) {
                displayAlertBox("Kavez je uspješno izmijenjen!");
                stage.close();
            }
        }
    }

    public void quit() {
        stage.close();
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

    private boolean checkName() {
        if("".equals(cageNameTextField.getText())) {
            displayAlertBox("Nije uneseno ime kaveza!");
            return false;
        }
        return true;
    }

    private boolean checkCapacity() {
        if("".equals(cageCapacityTextField.getText())) {
            displayAlertBox("Nije unesen kapacitet kaveza!");
            return false;
        }
        if(!checkNumber(cageCapacityTextField.getText())) {
            displayAlertBox("Unos za polje kapacitet nije odgovarajući!");
        }
        return true;
    }

    private boolean checkNumber(String content) {
        try {
            Integer.parseInt(content);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
