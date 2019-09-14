package GUI.adding_cage;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

public class AddingCageController {

    @FXML
    private TextField cageCapacityTextField;
    @FXML
    private TextField nameTextField;
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

    public void save() {
        if(checkCapacity() && checkName()) {
            if(AzilUtilities.getDAOFactory().getCageDAO().insert(new CageDTO(0, nameTextField.getText(), Integer.valueOf(cageCapacityTextField.getText().trim())))) {
                displayAlertBox("Kavez je uspješno dodat!");
            }
            quit();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkCapacity() {
        if("".equals(cageCapacityTextField.getText().trim())) {
            displayAlertBox("Niste unijeli kapacitet kaveza!");
            return false;
        } else if(!checkIsNumber(cageCapacityTextField.getText().trim())) {
            displayAlertBox("Unos za polje kapacitet nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            displayAlertBox("Niste unijeli naziv kaveza!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingCageController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkIsNumber(String number) {
        return number.matches("^[0-9]*$");
    }

}
