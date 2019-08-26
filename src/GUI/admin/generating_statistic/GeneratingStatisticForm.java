package GUI.admin.generating_statistic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneratingStatisticForm {

    public void display() throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("generatingStatistic.fxml"));
        stage.setTitle("Azil za pse - generisanje statistike");
        Scene scene = new Scene(root, 850, 700);
        stage.setScene(scene);
        stage.show();
    }
}
