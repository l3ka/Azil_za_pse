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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - udomljavanje psa");
        Scene scene = new Scene(loader.load(), 1200, 700);
        stage.setScene(scene);
        AdoptingDogController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }
}
