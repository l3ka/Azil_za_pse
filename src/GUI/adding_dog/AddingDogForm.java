package GUI.adding_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

public class AddingDogForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingDog.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - dodavanje psa");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(850);
        AddingDogController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
