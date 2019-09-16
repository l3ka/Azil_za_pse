package GUI.editing_medicine;

import data.dto.MedicineDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EditingMedicineForm {

    private MedicineDTO medicine;

    public EditingMedicineForm(MedicineDTO medicine) {
        this.medicine = medicine;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editingMedicine.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - izmjena lijeka");
        Scene scene = new Scene(loader.load());
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        EditingMedicineController controller = loader.getController();
        controller.initialize(stage, medicine);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
