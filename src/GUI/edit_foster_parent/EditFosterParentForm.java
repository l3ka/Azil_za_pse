package GUI.edit_foster_parent;

import data.dto.FosterParentDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EditFosterParentForm {

    private FosterParentDTO fosterParent;

    public EditFosterParentForm(FosterParentDTO fosterParent) {
        this.fosterParent = fosterParent;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editFosterParent.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "resources" + File.separator + "reportImages" + File.separator + "fosters.png"));
        stage.setTitle("Azil za pse - izmjena udomitelja");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(850);
        EditFosterParentController controller = loader.getController();
        controller.initialize(stage, fosterParent);
        stage.showAndWait();
    }

}
