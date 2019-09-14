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
        this.stage = stage;
        initButtonEvent();
    }

    private void initButtonEvent() {
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
    }

    public void addDrug() {
        if(checkName() && checkAmount() && checkDescription()) {
            AzilUtilities.getDAOFactory().getMedicineDAO().insert(new MedicineDTO(0, nameTextField.getText().trim(), descriptionTextField.getText().trim(), Integer.valueOf(amountTextField.getText().trim())));
            displayAlertBox("Lijek je uspješno dodat!");
            quit();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText().trim())) {
            displayAlertBox("Unos za polje naziv nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkAmount() {
        if("".equals(amountTextField.getText().trim()) || !checkNumber(amountTextField.getText().trim())) {
            displayAlertBox("Unos za polje količina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextField.getText().trim())) {
            displayAlertBox("Unos za polje opis nije odgovarajući!");
            return false;
        }
        return true;
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
