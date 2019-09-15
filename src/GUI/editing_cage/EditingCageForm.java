package GUI.editing_cage;

import data.dto.CageDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EditingCageForm {

    private CageDTO cage;

    public EditingCageForm(CageDTO cage) {
        this.cage = cage;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editingCage.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-cage-icon.png"));
        stage.setTitle("Azil za pse - izmjena kaveza");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        EditingCageController controller = loader.getController();
        controller.initialize(stage, cage);
        stage.showAndWait();
    }

}
