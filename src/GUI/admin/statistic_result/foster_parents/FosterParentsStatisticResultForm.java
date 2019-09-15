package GUI.admin.statistic_result.foster_parents;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class FosterParentsStatisticResultForm {

    private LocalDate dateFrom;

    public FosterParentsStatisticResultForm(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fosterParentsStatisticResult.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - rezultati statistike");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        FosterParentsStatisticResultController controller = loader.getController();
        controller.initialize(stage, dateFrom);
        stage.showAndWait();
    }

}
