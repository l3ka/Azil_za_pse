package GUI.employee.main_screen;

import data.dto.EmployeeDTO;
import data.dto.ServantDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen {
    private EmployeeDTO employee;

    public MainScreen(EmployeeDTO employee) {
        this.employee = employee;
    }

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(), 1000, 700);
        stage.setTitle("Azil za pse - glavni meni");
        stage.setResizable(false);
        stage.setScene(scene);
        MainScreenController controller = loader.getController();
        controller.initialize(stage);
        controller.setEmployee(employee);
        stage.show();
    }

}
