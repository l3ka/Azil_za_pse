package GUI.admin.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMainForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("mainFormAdmin.fxml"));
        stage.setTitle("Azil za pse - administratorski dio");
        stage.setResizable(false);
        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();

    }
}
