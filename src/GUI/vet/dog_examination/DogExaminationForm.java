package GUI.vet.dog_examination;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DogExaminationForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("dogExamination.fxml"));
        stage.setTitle("Azil za pse - pregled psa");
        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
}
