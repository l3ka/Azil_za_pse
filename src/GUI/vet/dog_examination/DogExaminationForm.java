package GUI.vet.dog_examination;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DogExaminationForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dogExamination.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - pregled psa");
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setScene(scene);
        DogExaminationController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
