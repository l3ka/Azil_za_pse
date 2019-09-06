package GUI.vet.generating_finding;

import GUI.alert_box.AlertBoxForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.MedicalResultDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class GeneratingFindingController {

    @FXML private TextArea descriptionTextArea;

    private Stage stage;
    private DogDTO dogForExamination = GeneratingFindingForm.dog;
    private EmployeeDTO veterinarian = GeneratingFindingForm.employee;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void save() {
        if(checkDescription()) {
            AzilUtilities.getDAOFactory().getMedicalResultDAO().insert(new MedicalResultDTO(0, descriptionTextArea.getText(), new Date(Calendar.getInstance().getTime().getTime()), dogForExamination.getDogId(), veterinarian.getJMB()));
            displayAlertBox("Nalaz je uspješno generisan!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextArea.getText())) {
            displayAlertBox("Nije unesen opis!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
