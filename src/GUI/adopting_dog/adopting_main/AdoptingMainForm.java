package GUI.adopting_dog.adopting_main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class AdoptingMainForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adoptingMain.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - udomljavanja");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1350);
        stage.setMinHeight(720);
        AdoptingMainController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
