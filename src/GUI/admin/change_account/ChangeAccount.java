package GUI.admin.change_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeAccount {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeAccount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(), 700, 700);
        stage.setTitle("Azil za pse - izmjena korisničkog naloga");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        ChangeAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }

}
