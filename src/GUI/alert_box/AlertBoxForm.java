package GUI.alert_box;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBoxForm {

    public static String text;

    public AlertBoxForm(String tekst) {
        AlertBoxForm.text = tekst;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("alertBox.fxml"));
        Stage stage = new Stage();
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
