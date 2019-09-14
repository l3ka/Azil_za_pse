package GUI.decide_box;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class DecideBox {

    private String message;

    public DecideBox(String message) {
        this.message = message;
    }

    public boolean display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DecideBox.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setResizable(false);
        DecideBoxController controller = loader.getController();
        controller.initialize(stage, message);
        stage.showAndWait();
        return controller.getDecision();
    }

}
