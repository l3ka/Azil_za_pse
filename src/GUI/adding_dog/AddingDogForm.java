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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        Scene scene = new Scene(loader.load(), 950, 780);
        stage.setScene(scene);
        AddingDogController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
