package GUI.employee.main_screen;

import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class MainScreen {

    private EmployeeDTO employee;

    public MainScreen(EmployeeDTO employee) {
        this.employee = employee;
    }

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - glavni meni");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(720);
        MainScreenController controller = loader.getController();
        controller.initialize(stage);
        controller.setEmployee(employee);
        stage.show();
    }

}
