package GUI.vet.taking_medicine;

import data.dto.EmployeeDTO;
import data.dto.MedicalResultDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

public class TakingMedicineForm {

    private EmployeeDTO employee;
    private MedicalResultDTO medicalResult;

    public TakingMedicineForm(EmployeeDTO employee, MedicalResultDTO medicalResult) {
        this.employee = employee;
        this.medicalResult = medicalResult;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("takingMedicine.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - uzimanje lijeka");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(720);
        TakingMedicineController controller = loader.getController();
        controller.initialize(stage, employee, medicalResult);
        stage.showAndWait();
    }

}
