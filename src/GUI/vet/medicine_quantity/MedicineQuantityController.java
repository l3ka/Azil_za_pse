package GUI.vet.medicine_quantity;

import GUI.alert_box.AlertBoxForm;
import data.dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.Calendar;
import java.util.Date;

public class MedicineQuantityController {

    @FXML
    private Label medicineNameLabel;
    @FXML
    private ComboBox<Integer> quantityComboBox;
    @FXML
    private Button saveButtonClick;
    @FXML
    private Button quitButtonClick;

    private Stage stage;
    private MedicineDTO medicine;
    private EmployeeDTO employee;
    private MedicalResultDTO medicalResult;

    public void initialize(Stage stage, MedicineDTO medicine, EmployeeDTO employee, MedicalResultDTO medicalResult) {
        this.stage = stage;
        this.medicine = medicine;
        this.employee = employee;
        this.medicalResult = medicalResult;
        medicineNameLabel.setText(medicine.getName());
        for(int i = 1; i <= medicine.getQuantity(); i++) {
            quantityComboBox.getItems().add(i);
        }
        initButtonEvent();
    }

    private void initButtonEvent() {
        saveButtonClick.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                saveButtonClick.fire();
                e.consume();
            }
        });
        quitButtonClick.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                quitButtonClick.fire();
                e.consume();
            }
        });
    }

    public void save() {
        if(checkSelectedQuantity()) {
            medicine.setQuantity(medicine.getQuantity() - quantityComboBox.getSelectionModel().getSelectedItem());
            AzilUtilities.getDAOFactory().getMedicineDAO().update(medicine);
            try {
                AzilUtilities.getDAOFactory().getTakingMedicineDAO().insert(new TakingMedicineDTO(new java.sql.Timestamp(new Date().getTime()), (VeterinarianDTO) employee, medicine, medicalResult, quantityComboBox.getSelectionModel().getSelectedItem()));
            }
            catch (Exception ex) {
                AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            }
            quit();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedQuantity() {
        if(quantityComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabrana količina!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
