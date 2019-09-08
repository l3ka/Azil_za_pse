package GUI.adding_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddingDogForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingDog.fxml"));
        Stage stage = new Stage();
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
