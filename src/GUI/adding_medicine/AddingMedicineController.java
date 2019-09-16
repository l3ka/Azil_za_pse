package GUI.adding_medicine;

import GUI.alert_box.AlertBoxForm;
import data.dto.LoggerDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class AddingMedicineController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            initButtonEvent();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - initialize", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void addDrug() {
        try {
            if(checkName() && checkAmount()) {
                AzilUtilities.getDAOFactory().getMedicineDAO().insert(new MedicineDTO(0, nameTextField.getText().trim(), descriptionTextField.getText().trim(), Integer.valueOf(amountTextField.getText().trim())));
                displayAlertBox("Lijek je uspješno dodat!");
                quit();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - addDrug", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - quit", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        try {
            if("".equals(nameTextField.getText().trim())) {
                displayAlertBox("Unos za polje naziv nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - checkName", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkAmount() {
        try {
            if("".equals(amountTextField.getText().trim()) || !checkNumber(amountTextField.getText().trim())) {
                displayAlertBox("Unos za polje količina nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - checkAmount", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - checkNumber", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingMedicineController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

}
