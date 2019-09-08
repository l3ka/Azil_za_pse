package GUI.adopting_dog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdoptingDogForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adoptingDog.fxml"));
        Stage stage = new Stage();
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
