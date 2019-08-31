package GUI.admin.add_change_account;

import GUI.employee.main_screen.MainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAndChangeAccount {

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAndChangeAccount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(), 700, 700);
        stage.setTitle("Azil za pse - korisnički nalozi");
        stage.setHeight(700);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        AddAndChangeAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
    }

}
