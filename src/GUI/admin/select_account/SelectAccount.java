package GUI.admin.select_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectAccount {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectAccount.fxml"));
        Stage stage = new Stage();
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
