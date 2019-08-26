package GUI.employee.main_screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(), 700, 700);
        stage.setTitle("Azil za pse - glavni meni");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        MainScreenController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }
}
