package GUI.add_foster_parent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddFosterParent {

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFosterParent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Azil za pse - dodavanje udomitelja");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        AddFosterParentController controller = loader.getController();
        controller.initialize(stage);
        stage.showAndWait();
    }
}
