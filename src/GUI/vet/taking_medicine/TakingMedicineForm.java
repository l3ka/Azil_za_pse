package GUI.vet.taking_medicine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TakingMedicineForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("takingMedicine.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - uzimanje lijeka");
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setScene(scene);
        TakingMedicineController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
