package GUI.admin.add_account;

import GUI.alert_box.AlertBoxForm;
import data.dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class AddAccountController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private TextField qualificationsTextField;
    @FXML
    private TextField identificationNumberTextField;
    @FXML
    private TextField placeOfResidenceTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<String> positionComboBox;
    @FXML
    private TextField salaryTextField;
    @FXML
    private DatePicker contractFromDatePicker;
    @FXML
    private DatePicker contractToDatePicker;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            positionComboBox.getItems().add("Administrator");
            positionComboBox.getItems().add("Veterinar");
            positionComboBox.getItems().add("Sluzbenik");
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - initialize", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            saveButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    saveButton.fire();
                    e.consume();
                }
            });
            quitButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    quitButton.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void save() {
        try {
            if(checkUsername() && checkPassword() && checkName() && checkQualifications() && checkIdentificationNumber() && checkIdentificationNumber() && checkPlaceOfResidence() && checkPhoneNumber() && checkPosition() && checkSalary() && checkValidUntil()) {

                EmploymentContractDTO employmentContract = new EmploymentContractDTO(0, 1,positionComboBox.getSelectionModel().getSelectedItem(),
                        java.sql.Date.valueOf(contractFromDatePicker.getValue()), null, Double.valueOf(salaryTextField.getText().trim()));
                if (contractToDatePicker.getValue() != null) {
                    employmentContract.setValidationDate(java.sql.Date.valueOf(contractToDatePicker.getValue()));
                }
                boolean result = false;
                switch(positionComboBox.getSelectionModel().getSelectedItem()) {
                    case "Administrator":
                        result = AzilUtilities.getDAOFactory().getAdministratorDAO().insert(new AdministratorDTO(usernameTextField.getText().trim(), passwordField.getText().trim(),
                                nameTextField.getText().trim(), surnameTextField.getText().trim(), qualificationsTextField.getText().trim(), placeOfResidenceTextField.getText().trim(),
                                phoneNumberTextField.getText(), identificationNumberTextField.getText()), employmentContract);
                        break;
                    case "Veterinar":
                        result = AzilUtilities.getDAOFactory().getVeterinarinaDAO().insert(new VeterinarianDTO(usernameTextField.getText().trim(), passwordField.getText().trim(),
                                nameTextField.getText().trim(), surnameTextField.getText().trim(), qualificationsTextField.getText().trim(), placeOfResidenceTextField.getText().trim(),
                                phoneNumberTextField.getText().trim(), identificationNumberTextField.getText().trim()), employmentContract);
                        break;
                    case "Sluzbenik":
                        result = AzilUtilities.getDAOFactory().getServantDAO().insert(new ServantDTO(usernameTextField.getText().trim(), passwordField.getText().trim(),
                                nameTextField.getText().trim(), surnameTextField.getText().trim(), qualificationsTextField.getText().trim(), placeOfResidenceTextField.getText().trim(),
                                phoneNumberTextField.getText().trim(), identificationNumberTextField.getText().trim()), employmentContract);
                        break;
                    default:
                        break;
                }
                if (result) {
                    displayAlertBox("Nalog je uspješno dodat u sistem!");
                    quit();
                }
                else {
                    displayAlertBox("Desila se greška prilikom dodavanja naloga u sistem!");
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - save", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - quit", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkUsername() {
        try {
            if("".equals(usernameTextField.getText().trim())) {
                displayAlertBox("Unos za korisničko ime nije odgovarajući!");
                return false;
            }
            else if(AzilUtilities.getDAOFactory().getEmployeeDAO().exists(usernameTextField.getText().trim(), identificationNumberTextField.getText().trim())) {
                displayAlertBox("Korisničko ime ili JMBG postoji u bazi!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkUsername", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPassword() {
        try {
            if("".equals(passwordField.getText().trim()) || "".equals(repeatPasswordField.getText().trim()) || !repeatPasswordField.getText().trim().equals(passwordField.getText().trim())) {
                displayAlertBox("Unos za lozinku nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkPassword", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkName", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkQualifications() {
        try {
            if ("".equals(qualificationsTextField.getText().trim())) {
                displayAlertBox("Unos za polje stručna sprema nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkQualifications", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkIdentificationNumber() {
        try {
            if ("".equals(identificationNumberTextField.getText().trim()) || !checkIsNumber(identificationNumberTextField.getText().trim())) {
                displayAlertBox("Unos za polje JMB nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkIdentificationNumber", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPlaceOfResidence() {
        try {
            if ("".equals(placeOfResidenceTextField.getText().trim())) {
                displayAlertBox("Unos za polje mjesto stanovanja nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkPlaceOfResidence", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkPhoneNumber", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPosition() {
        try {
            if (positionComboBox.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabrana pozicija!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkPosition", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkSalary() {
        try {
            if ("".equals(salaryTextField.getText().trim())) {
                displayAlertBox("Unos za polje plata nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkSalary", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkValidUntil() {
        try {
            if ("".equals(contractFromDatePicker.toString().trim())) {
                displayAlertBox("Unos za polje ugovor važi do nije odgovarajući!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkValidUntil", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkIsNumber(String number) {
        try {
            return number.matches("^[0-9]*$");
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - checkIsNumber", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddAccountController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

}
