package GUI.vet.medicine_quantity;

import GUI.alert_box.AlertBoxForm;
import data.dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MedicineQuantityController {

    @FXML
    private Label medicineNameLabel;
    @FXML
    private ComboBox<Integer> quantityComboBox;

    private Stage stage;
    private MedicineDTO medicine;
    private EmployeeDTO employee;
    private MedicalResultDTO medicalResult;

    public void initialize(Stage stage,MedicineDTO medicine,EmployeeDTO employee,MedicalResultDTO medicalResult) {
        this.stage = stage;
        this.medicine = medicine;
        this.employee = employee;
        this.medicalResult = medicalResult;
        medicineNameLabel.setText(medicine.getName());
        for(int i = 1; i <= medicine.getQuantity(); i++) {
            quantityComboBox.getItems().add(i);
        }
    }

    public void save() {
        if(checkSelectedQuantity()) {
            medicine.setQuantity(medicine.getQuantity() - quantityComboBox.getSelectionModel().getSelectedItem());
            AzilUtilities.getDAOFactory().getMedicineDAO().update(medicine);
            try {
                System.out.println( quantityComboBox.getSelectionModel().getSelectedItem());
                AzilUtilities.getDAOFactory().getTakingMedicineDAO().insert(new TakingMedicineDTO(new java.sql.Timestamp(new Date().getTime()), (VeterinarianDTO) employee, medicine, medicalResult, quantityComboBox.getSelectionModel().getSelectedItem()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
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

        }
    }
}
