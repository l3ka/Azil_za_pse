package GUI.admin.add_change_account;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAndChangeAccountController {

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
        if (checkName() && checkQualifications() && checkPlaceOfResidence() &&
                checkIdentificationNumber() && checkPhoneNumber()) {
            try {
                new AlertBoxForm("Nalog je uspješno sačuvan!").display();
            } catch(Exception exception) {

            }
        }
    }

    private boolean checkName() {
        if ("".equals(nameTextField.getText()) || "".equals(surnameTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polja ime i prezime nije odgovarajući!").display();
            } catch (Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkQualifications() {
        if ("".equals(qualificationsTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje stručna sprema nije odgovarajući!").display();
            } catch (Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkIdentificationNumber() {
        String identificationNumber = identificationNumberTextField.getText();
        if ("".equals(identificationNumber) || !checkNumber(identificationNumber)) {
            try {
                new AlertBoxForm("Unos za polje JMB nije odgovarajući!").display();
            } catch (Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkPlaceOfResidence() {
        if ("".equals(placeOfResidenceTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje mjesto stanovanja nije odgovarajući!").display();
            } catch (Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkPhoneNumber() {
        if ("".equals(phoneNumberTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje broj telefona nije odgovarajući!").display();
            } catch (Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

}