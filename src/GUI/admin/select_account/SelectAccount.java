package GUI.admin.select_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class SelectAccount {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectAccount.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - izbor naloga");
        Scene scene = new Scene(loader.load());
        SelectAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(700);
        stage.showAndWait();
    }

}
