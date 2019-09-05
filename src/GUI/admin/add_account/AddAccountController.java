package GUI.admin.add_account;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAccountController {

    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField qualificationsTextField;
    @FXML
    private TextField identificationNumberTextField;
    @FXML
    private TextField placeOfResidenceTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private TextField contractValidUntilTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void save() {
        if(checkName() && checkQualifications() && checkIdentificationNumber() && checkIdentificationNumber() && checkPlaceOfResidence() && checkPhoneNumber() && checkPosition() && checkSalary() && checkValidUntil()) {
            // DODATI NALOG
        }
    }

    public void quit() {
        stage.close();
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

    private boolean checkPosition() {
        if ("".equals(positionTextField.getText().trim())) {
            displayAlertBox("Unos za polje pozicija nije odgovarajući!");
            return false;
        }
        return true;
    }


    private boolean checkSalary() {
        if ("".equals(salaryTextField.getText().trim())) {
            displayAlertBox("Unos za polje plata nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkValidUntil() {
        if ("".equals(contractValidUntilTextField.getText().trim())) {
            displayAlertBox("Unos za polje ugovor važi do nije odgovarajući!");
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
