package GUI.vet.generating_finding;

import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

public class GeneratingFindingForm {

    public static DogDTO dog;
    public static EmployeeDTO employee;

    public GeneratingFindingForm(DogDTO dog, EmployeeDTO employee) {
        GeneratingFindingForm.dog = dog;
        GeneratingFindingForm.employee = employee;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("generatingFinding.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - generisanje nalaza");
        Scene scene = new Scene(loader.load());
        stage.setMinHeight(720);
        stage.setMinWidth(700);
        GeneratingFindingController controller = loader.getController();
        controller.initialize(stage);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
