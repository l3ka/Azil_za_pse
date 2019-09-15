package GUI.admin.change_account;

import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChangeAccount {

    public static EmployeeDTO employee;

    public ChangeAccount(EmployeeDTO employee) {
        ChangeAccount.employee = employee;
    }

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeAccount.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - izmjena korisničkog naloga");
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(700);
        ChangeAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
