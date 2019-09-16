package GUI.preview.foster_parents_preview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class FosterParentsForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fosterParentsPreview.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "resources" + File.separator + "reportImages" + File.separator + "fosters.png"));
        stage.setTitle("Azil za pse - udomitelji");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(850);
        FosterParentsController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
