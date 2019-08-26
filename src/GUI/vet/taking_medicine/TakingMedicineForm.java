package GUI.vet.taking_medicine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TakingMedicineForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("takingMedicine.fxml"));
        stage.setTitle("Azil za pse - uzimanje lijeka");
        Scene scene = new Scene(root, 1000, 780);
        stage.setScene(scene);
        stage.show();
    }
}
