package GUI.add_foster_parent;

import GUI.alert_box.AlertBoxForm;
import data.dto.FosterParentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

public class AddFosterParentController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField identificationNumberTextField;
    @FXML
    private TextField placeOfResidenceTextField;
    @FXML
    private TextField phoneNumberTextField;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void quit() {
        stage.close();
    }
    public void addFosterParent() {
        if (checkName() && checkIsNumber(identificationNumberTextField.getText()) && checkPhoneNumber() && checkPlaceOfResidence()) {
            if (AzilUtilities.getDAOFactory().getFosterParentDAO().insert
                    (new FosterParentDTO(identificationNumberTextField.getText(),
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            placeOfResidenceTextField.getText(),
                            phoneNumberTextField.getText()))) {
                displayAlertBox("Udomitelj je uspješno dodat!");
                stage.close();
            }
        }
    }

    private boolean checkName() {
        if ("".equals(nameTextField.getText().trim()) || "".equals(surnameTextField.getText().trim())) {
            displayAlertBox("Unos za polja ime i prezime nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkIsNumber(String number) {
        return number.matches("^[0-9]*$");
    }

    private boolean checkPlaceOfResidence() {
        if ("".equals(placeOfResidenceTextField.getText().trim())) {
            displayAlertBox("Unos za polje mjesto stanovanja nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkPhoneNumber() {
        if ("".equals(phoneNumberTextField.getText().trim())) {
            displayAlertBox("Unos za polje broj telefona nije odgovarajući!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
