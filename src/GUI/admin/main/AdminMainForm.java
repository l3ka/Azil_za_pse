package GUI.admin.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMainForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFormAdmin.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - administratorski dio");
        stage.setResizable(false);
        Scene scene = new Scene(loader.load(), 1000, 800);
        stage.setScene(scene);
        AdminMainController controller = loader.getController();
        controller.initialize(stage);
        stage.show();

    }
}
