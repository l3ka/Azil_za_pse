package GUI.deactivated_accounts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class DeactivatedAccountsForm {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deactivatedAccounts.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "resources" + File.separator + "reportImages" + File.separator + "employee.png"));
        stage.setTitle("Azil za pse - deaktivirani nalozi");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(1300);
        DeactivatedAccountsController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
