package GUI.admin.change_account;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

public class ChangeAccountController {

    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField qualificationsTextField;
    @FXML
    private TextField identificationNumberTextField;
    @FXML
    private TextField placeOfResidenceTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void saveAccount() {
        if (checkName() && checkQualifications() && checkPlaceOfResidence() && checkIdentificationNumber() && checkPhoneNumber()) {

            String username = "administrator";      // SAMO PRIVREMENO DOK NESTO NE RIJESIMO ZA OVO DA SE MOZE DODATI I USERNAME I PASSWORD
            String password = "password";

            if (AzilUtilities.getDAOFactory().getEmployeeDAO().exists(username, identificationNumberTextField.getText().trim())) {
                displayAlertBox("User with this username or JMB already exists in database!");
            }
            else {
                // TODO: NAPRAVITI EDIT/DODAVANJE NALOGA KADA ERCEG SREDI FORMU
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

    private boolean checkQualifications() {
        if ("".equals(qualificationsTextField.getText().trim())) {
            displayAlertBox("Unos za polje stručna sprema nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkIdentificationNumber() {
        if ("".equals(identificationNumberTextField.getText().trim()) || !checkIsNumber(identificationNumberTextField.getText().trim())) {
            displayAlertBox("Unos za polje JMB nije odgovarajući!");
            return false;
        }
        return true;
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

    private boolean checkIsNumber(String number) {
        return number.matches("^[0-9]*$");
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}