package GUI.vet.generating_finding;

import GUI.alert_box.AlertBoxForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import data.dto.MedicalResultDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class GeneratingFindingController {

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private DogDTO dogForExamination = GeneratingFindingForm.dog;
    private EmployeeDTO veterinarian = GeneratingFindingForm.employee;

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
        if(checkDescription()) {
            AzilUtilities.getDAOFactory().getMedicalResultDAO().insert(new MedicalResultDTO(0, descriptionTextArea.getText().trim(), new Date(Calendar.getInstance().getTime().getTime()), dogForExamination.getDogId(), veterinarian.getJMB()));
            displayAlertBox("Nalaz je uspješno generisan!");
            quit();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextArea.getText().trim())) {
            displayAlertBox("Nije unesen opis!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(veterinarian.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
