package GUI.vet.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VetMainForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("mainFormVet.fxml"));
        stage.setTitle("Azil za pse - veterinarski dio");
        Scene scene = new Scene(root, 1000, 780);
        stage.setScene(scene);
        stage.show();
    }
}
