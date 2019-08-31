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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Upozorenje");
        Scene scena = new Scene(loader.load(), 500, 380);
        stage.setScene(scena);
        AlertBoxController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
