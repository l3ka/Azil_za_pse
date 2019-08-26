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
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("alertBox.fxml"));
        stage.setTitle("Upozorenje");
        Scene scena = new Scene(root, 500, 380);
        stage.setScene(scena);
        stage.showAndWait();
    }
}
