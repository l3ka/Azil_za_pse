package GUI.admin.add_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddAccount {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAccount.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - dodavanje korisničkog naloga");
        stage.setScene(scene);
        stage.setMinHeight(725);
        stage.setMinWidth(700);
        AddAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
