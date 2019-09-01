package GUI.vet.generating_finding;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneratingFindingForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("generatingFinding.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - generisanje nalaza");
        Scene scene = new Scene(loader.load(), 850, 700);
        GeneratingFindingController controller = loader.getController();
        controller.initialize(stage);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
