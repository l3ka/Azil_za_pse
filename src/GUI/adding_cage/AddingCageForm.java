package GUI.adding_cage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class AddingCageForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingCage.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - dodavanje kaveza");
        Scene scene = new Scene(loader.load());
        stage.setMinHeight(700);
        stage.setMinWidth(850);
        stage.setScene(scene);
        AddingCageController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
