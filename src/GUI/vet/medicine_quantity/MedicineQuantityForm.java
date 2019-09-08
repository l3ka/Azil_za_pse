package GUI.vet.medicine_quantity;

import data.dto.EmployeeDTO;
import data.dto.MedicalResultDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

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
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
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
