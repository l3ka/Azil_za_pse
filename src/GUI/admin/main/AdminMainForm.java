package GUI.admin.main;

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
        stage.setTitle("Azil za pse - administratorski dio");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(720);
        AdminMainController controller = loader.getController();
        controller.setEmployee(employee);
        controller.initialize(stage);
        stage.show();
    }

}
