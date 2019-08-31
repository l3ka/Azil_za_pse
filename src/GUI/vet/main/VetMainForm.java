package GUI.vet.main;

import data.dto.EmployeeDTO;
import data.dto.VeterinarianDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VetMainForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFormVet.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - veterinarski dio");
        Scene scene = new Scene(loader.load(), 1000, 780);
        stage.setScene(scene);
        VetMainController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }

    public void display(EmployeeDTO employee) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFormVet.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - veterinarski dio");
        Scene scene = new Scene(loader.load(), 1000, 780);
        stage.setScene(scene);
        VetMainController controller = loader.getController();
        controller.setVeterinarian(employee);
        controller.initialize(stage);
        stage.show();
    }

}
