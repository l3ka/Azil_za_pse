package GUI.vet.main;

import data.dto.EmployeeDTO;
import data.dto.VeterinarianDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VetMainForm {

    private EmployeeDTO employee;

    public VetMainForm(EmployeeDTO employee) {
        this.employee = employee;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFormVet.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - veterinarski dio");
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setScene(scene);
        VetMainController controller = loader.getController();
        controller.initialize(stage);
        controller.setEmployee(employee);
        stage.show();
    }

}
