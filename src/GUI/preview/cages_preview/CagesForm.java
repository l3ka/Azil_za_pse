package GUI.preview.cages_preview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class CagesForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cagesPreview.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-cage-icon.png"));
        stage.setTitle("Azil za pse - kavezi");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(1200);
        CagesController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
