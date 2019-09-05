package GUI.admin.select_account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectAccount {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectAccount.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Azil za pse - izbor naloga");
        stage.setResizable(false);
        Scene scene = new Scene(loader.load(), 850, 700);
        SelectAccountController controller = loader.getController();
        controller.initialize(stage);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
