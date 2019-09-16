package GUI.employment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EmploymentForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("employment.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - zaposljavanje");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinWidth(1400);
        stage.setMinHeight(700);
        EmploymentController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
