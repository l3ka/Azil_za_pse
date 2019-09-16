package GUI.admin.change_account;

import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class ChangeAccountController {

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
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private EmployeeDTO employeeForEdit = ChangeAccount.employee;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            nameTextField.setText(employeeForEdit.getName());
            surnameTextField.setText(employeeForEdit.getSurname());
            qualificationsTextField.setText(employeeForEdit.getQualifications());
            identificationNumberTextField.setText(employeeForEdit.getJMB());
            placeOfResidenceTextField.setText(employeeForEdit.getResidenceAddress());
            phoneNumberTextField.setText(employeeForEdit.getTelephoneNumber());
            usernameTextField.setText(employeeForEdit.getUsername());
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void saveAccount() {
        try {
            if (checkUsername() && checkName() && checkQualifications() && checkPlaceOfResidence() && checkPhoneNumber()) {
                boolean flag = true;
                if (!employeeForEdit.getName().equals(nameTextField.getText().trim()) || !employeeForEdit.getSurname().equals(surnameTextField.getText().trim()) ||
                        !employeeForEdit.getQualifications().equals(qualificationsTextField.getText().trim()) || !employeeForEdit.getResidenceAddress().equals(placeOfResidenceTextField.getText()) ||
                        !employeeForEdit.getTelephoneNumber().equals(phoneNumberTextField.getText().trim()) || !employeeForEdit.getUsername().equals(usernameTextField.getText().trim())) {
                    employeeForEdit.setName(nameTextField.getText().trim());
                    employeeForEdit.setSurname(surnameTextField.getText().trim());
                    employeeForEdit.setQualifications(qualificationsTextField.getText().trim());
                    employeeForEdit.setResidenceAddress(placeOfResidenceTextField.getText().trim());
                    employeeForEdit.setTelephoneNumber(phoneNumberTextField.getText().trim());
                    employeeForEdit.setUsername(usernameTextField.getText().trim());
                    if (checkEqualityPassword()) {
                        employeeForEdit.setPassword(AzilUtilities.getSHA256(passwordField.getText().trim()));
                        flag = false;
                    }
                    AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
                    displayAlertBox("Uspješno izmijenjen nalog!");
                    quit();
                }
                else if (checkEqualityPassword() && flag) {
                    employeeForEdit.setPassword(AzilUtilities.getSHA256(passwordField.getText().trim()));
                    AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
                    displayAlertBox("Uspješno izmijenjen nalog!");
                    quit();
                }
                else {
                    displayAlertBox("Molimo unesite parametre kako biste izmijenili nalog!");
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - saveAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkUsername() {
        try {
            if("".equals(usernameTextField.getText().trim())) {
                displayAlertBox("Nije uneseno korisničko ime!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkUsername", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkQualifications", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkPlaceOfResidence", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkPhoneNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkEqualityPassword() {
        try {
            if (!"".equals(passwordField.getText().trim()) && !"".equals(repeatPasswordField.getText().trim()) && passwordField.getText().trim().equals(repeatPasswordField.getText().trim())) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - checkEqualityPassword", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("ChangeAccountController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}