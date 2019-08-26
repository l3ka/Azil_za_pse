package GUI.adopting_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdoptingDogForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("adoptingDog.fxml"));
        stage.setTitle("Azil za pse - udomljavanje psa");
        Scene scene = new Scene(root, 1300, 780);
        stage.setScene(scene);
        stage.show();
    }
}
