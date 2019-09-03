package GUI.admin.add_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAccount {
    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAccount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - dodavanje korisničkog naloga");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setResizable(false);
        AddAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }
}
