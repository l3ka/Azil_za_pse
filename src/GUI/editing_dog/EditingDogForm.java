package GUI.editing_dog;

import data.dto.DogDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EditingDogForm {

    private DogDTO dog;

    public EditingDogForm(DogDTO dog) {
        this.dog = dog;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editingDog.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog.png"));
        stage.setTitle("Azil za pse - izmjena psa");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(800);
        EditingDogController controller = loader.getController();
        controller.initialize(stage, dog);
        stage.showAndWait();
    }

}
