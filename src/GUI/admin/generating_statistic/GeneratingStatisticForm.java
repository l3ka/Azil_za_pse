package GUI.admin.generating_statistic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class GeneratingStatisticForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("generatingStatistic.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - generisanje statistike");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        GeneratingStatisticController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
