package GUI.login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class LoginForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - prijava");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(620);
        stage.setMinWidth(600);
        LoginController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
