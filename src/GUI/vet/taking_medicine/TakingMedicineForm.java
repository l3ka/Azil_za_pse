package GUI.vet.taking_medicine;

import data.dto.EmployeeDTO;
import data.dto.TakingMedicineDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TakingMedicineForm {
    public static EmployeeDTO employee;

    public TakingMedicineForm(EmployeeDTO employee) {
        TakingMedicineForm.employee = employee;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("takingMedicine.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - uzimanje lijeka");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        TakingMedicineController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
