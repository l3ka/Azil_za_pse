package GUI.alert_box;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class AlertBoxForm {

    public static String text;

    public AlertBoxForm(String tekst) {
        AlertBoxForm.text = tekst;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("alertBox.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "notification-icon.png"));
        stage.setTitle("Upozorenje");
        Scene scena = new Scene(loader.load());
        stage.setScene(scena);
        stage.setMinHeight(420);
        stage.setMinWidth(600);
        AlertBoxController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
