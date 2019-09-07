package GUI.vet.dog_examination;

import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.VeterinarianDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        stage.setTitle("Azil za pse - pregled psa");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        DogExaminationController controller = loader.getController();
        controller.initialize(stage,dog, employee);
        stage.showAndWait();
    }
}
