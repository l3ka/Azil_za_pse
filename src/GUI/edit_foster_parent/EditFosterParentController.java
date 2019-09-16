package GUI.edit_foster_parent;

import GUI.alert_box.AlertBoxForm;
import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class EditFosterParentController {

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
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private FosterParentDTO fosterParent;

    public void initialize(Stage stage, FosterParentDTO fosterParent) {
        try {
            this.stage = stage;
            this.fosterParent = fosterParent;

            nameTextField.setText(fosterParent.getName());
            surnameTextField.setText(fosterParent.getSurname());
            identificationNumberTextField.setText(fosterParent.getJMB());
            placeOfResidenceTextField.setText(fosterParent.getResidenceAddress());
            phoneNumberTextField.setText(fosterParent.getTelephoneNumber());
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void save() {
        try {
            if(checkName() && checkIsNumber(identificationNumberTextField.getText()) && checkPhoneNumber() && checkPlaceOfResidence()) {
                fosterParent.setName(nameTextField.getText());
                fosterParent.setSurname(surnameTextField.getText());
                fosterParent.setJMB(identificationNumberTextField.getText());
                fosterParent.setResidenceAddress(placeOfResidenceTextField.getText());
                fosterParent.setTelephoneNumber(phoneNumberTextField.getText());
                if(AzilUtilities.getDAOFactory().getFosterParentDAO().update(fosterParent)) {
                    displayAlertBox("Uspješno izmijenjeni podaci o udomitelju!");
                    quit();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - save", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        try {
            if ("".equals(nameTextField.getText().trim()) || "".equals(surnameTextField.getText().trim())) {
                displayAlertBox("Unos za polja ime i prezime nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkIsNumber(String number) {
        return number.matches("^[0-9]*$");
    }

    private boolean checkPlaceOfResidence() {
        try {
            if ("".equals(placeOfResidenceTextField.getText().trim())) {
                displayAlertBox("Unos za polje mjesto stanovanja nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - checkPlaceOfResidence", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPhoneNumber() {
        try {
            if ("".equals(phoneNumberTextField.getText().trim())) {
                displayAlertBox("Unos za polje broj telefona nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - checkPhoneNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditFosterParentController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
