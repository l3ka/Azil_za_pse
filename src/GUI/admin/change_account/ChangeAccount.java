package GUI.admin.change_account;

import data.dto.EmployeeDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeAccount {

    public static EmployeeDTO employee;

    public ChangeAccount(EmployeeDTO employee) {
        ChangeAccount.employee = employee;
    }

    public void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeAccount.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Azil za pse - izmjena korisničkog naloga");
        stage.setScene(scene);
        ChangeAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }

}
