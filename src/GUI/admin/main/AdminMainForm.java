package GUI.admin.main;

import data.dto.AdministratorDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMainForm {
    private EmployeeDTO employee;

    public AdminMainForm(EmployeeDTO employee) {
        this.employee = employee;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFormAdmin.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - administratorski dio");
        stage.setResizable(false);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        AdminMainController controller = loader.getController();
        controller.setEmployee(employee);
        controller.initialize(stage);
        stage.show();
    }

}
