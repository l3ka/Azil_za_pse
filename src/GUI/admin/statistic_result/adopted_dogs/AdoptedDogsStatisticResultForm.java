package GUI.admin.statistic_result.adopted_dogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.time.LocalDate;

public class AdoptedDogsStatisticResultForm {

    private LocalDate dateFrom;

    public AdoptedDogsStatisticResultForm(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adoptedDogsStatisticResult.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:" + "src" + File.separator + "GUI" + File.separator + "icons" + File.separator + "dog-icon.png"));
        stage.setTitle("Azil za pse - rezultati statistike");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        AdoptedDogsStatisticResultController controller = loader.getController();
        controller.initialize(stage, dateFrom);
        stage.showAndWait();
    }

}
