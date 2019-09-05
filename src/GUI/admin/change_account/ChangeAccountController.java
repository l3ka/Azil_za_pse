package GUI.admin.change_account;

import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private EmployeeDTO employeeForEdit = ChangeAccount.employee;

    public void initialize(Stage stage) {
        this.stage = stage;

        nameTextField.setText(employeeForEdit.getName());
        surnameTextField.setText(employeeForEdit.getSurname());
        qualificationsTextField.setText(employeeForEdit.getQualifications());
        identificationNumberTextField.setText(employeeForEdit.getJMB());
        placeOfResidenceTextField.setText(employeeForEdit.getResidenceAddress());
        phoneNumberTextField.setText(employeeForEdit.getTelephoneNumber());
    }

    public void saveAccount() {
        if (checkUsername() && checkPassword() && checkName() && checkQualifications() && checkPlaceOfResidence() && checkIdentificationNumber() && checkPhoneNumber()) {

            if (!employeeForEdit.getUsername().equals(usernameTextField.getText().trim())) {
                if (AzilUtilities.getDAOFactory().getEmployeeDAO().exists(usernameTextField.getText(), identificationNumberTextField.getText().trim())) {
                    displayAlertBox("User with this username or JMB already exists in database!");
                } else {
                    employeeForEdit.setUsername(usernameTextField.getText());
                    employeeForEdit.setPassword(passwordTextField.getText());
                    AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
                }
            }

            if (!employeeForEdit.getJMB().equals(identificationNumberTextField)) {
                employeeForEdit.setJMB(identificationNumberTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            if (!employeeForEdit.getName().equals(nameTextField.getText())) {
                employeeForEdit.setName(nameTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            if (!employeeForEdit.getSurname().equals(surnameTextField.getText())) {
                employeeForEdit.setSurname(surnameTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            if (!employeeForEdit.getQualifications().equals(qualificationsTextField.getText())) {
                employeeForEdit.setQualifications(qualificationsTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            if (!employeeForEdit.getResidenceAddress().equals(placeOfResidenceTextField.getText())) {
                employeeForEdit.setResidenceAddress(placeOfResidenceTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            if (!employeeForEdit.getTelephoneNumber().equals(phoneNumberTextField.getText())) {
                employeeForEdit.setTelephoneNumber(phoneNumberTextField.getText());
                AzilUtilities.getDAOFactory().getEmployeeDAO().update(employeeForEdit);
            }
            displayAlertBox("Uspješno izmijenjen nalog!");
        }
    }


    public void quit() {
        stage.close();
    }


    private boolean checkUsername() {
        if("".equals(usernameTextField.getText().trim())) {
            displayAlertBox("Nije uneseno korisničko ime!");
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        if("".equals(passwordTextField.getText().trim())) {
            displayAlertBox("Nije unesena lozinka!");
            return false;
        }
        return true;
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