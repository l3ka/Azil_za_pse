package GUI.adopting_dog.adopt_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

public class AdoptingDogForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adoptingDog.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - udomljavanje psa");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(720);
        AdoptingDogController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
