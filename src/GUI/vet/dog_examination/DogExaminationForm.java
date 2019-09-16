package GUI.vet.dog_examination;

import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;

public class DogExaminationForm {

    private EmployeeDTO employee;
    private DogDTO dog;

    public DogExaminationForm(DogDTO dog, EmployeeDTO employee) {
        this.dog = dog;
        this.employee = employee;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dogExamination.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - pregled psa");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(720);
        DogExaminationController controller = loader.getController();
        controller.initialize(stage,dog, employee);
        stage.showAndWait();
    }

}
