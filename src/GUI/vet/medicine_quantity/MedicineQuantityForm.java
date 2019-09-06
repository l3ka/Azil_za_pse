package GUI.vet.medicine_quantity;

import GUI.vet.taking_medicine.TakingMedicineController;
import data.dto.MedicineDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MedicineQuantityForm {

    public static MedicineDTO medicine;

    public MedicineQuantityForm(MedicineDTO medicine) {
        MedicineQuantityForm.medicine = medicine;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("medicineQuantity.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - količina lijeka");
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setScene(scene);
        MedicineQuantityController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
