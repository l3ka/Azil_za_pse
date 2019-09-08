package GUI.vet.medicine_quantity;

import GUI.vet.taking_medicine.TakingMedicineController;
import data.dto.EmployeeDTO;
import data.dto.MedicalResultDTO;
import data.dto.MedicineDTO;
import data.dto.VeterinarianDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MedicineQuantityForm {

    private MedicineDTO medicine;
    private EmployeeDTO employee;
    private MedicalResultDTO medicalResult;

    public MedicineQuantityForm(MedicineDTO medicine, EmployeeDTO employee, MedicalResultDTO medicalResult) {
        this.medicine = medicine;
        this.employee = employee;
        this.medicalResult = medicalResult;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("medicineQuantity.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - količina lijeka");
        Scene scene = new Scene(loader.load());
        stage.setMinHeight(420);
        stage.setMinWidth(600);
        stage.setScene(scene);
        MedicineQuantityController controller = loader.getController();
        controller.initialize(stage, medicine, employee, medicalResult);
        stage.showAndWait();
    }
}
