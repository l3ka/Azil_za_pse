package GUI.preview.medicine_preview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MedicinePreviewForm {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedicinePreview.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - lijekovi");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(720);
        MedicinePreviewController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }

}
