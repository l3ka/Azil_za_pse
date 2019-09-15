package GUI.login;

import data.dto.LoggerDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

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
        try {
            launch(args);
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginForm - main", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
