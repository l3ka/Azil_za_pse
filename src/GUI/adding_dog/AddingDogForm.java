package GUI.adding_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddingDogForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("addingDog.fxml"));
        stage.setTitle("Azil za pse - dodavanje psa");
        Scene scene = new Scene(root, 950, 780);
        stage.setScene(scene);
        stage.show();
    }
}
