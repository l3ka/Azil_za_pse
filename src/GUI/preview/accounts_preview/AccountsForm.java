package GUI.preview.accounts_preview;

import GUI.editing_cage.EditingCageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class AccountsForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editingCage.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "resources" + File.separator + "reportImages" + File.separator + "employee.png"));
        stage.setTitle("Azil za pse - nalozi");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(1300);
        AccountsController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
