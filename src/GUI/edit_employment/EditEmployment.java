package GUI.edit_employment;

import data.dto.EmployeeContractDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EditEmployment {

    public void display(EmployeeContractDTO employeeContract) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditEmployment.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - izmjena zapošljavanja");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(720);
        EditEmploymentController controller = loader.getController();
        controller.initialize(stage, employeeContract);
        stage.showAndWait();
    }

}
