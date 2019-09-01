package GUI.vet.dog_examination;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class DogExaminationController {
    @FXML TableView<String> dogsTableView;
    private File finding;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void chooseFinding() {
        FileChooser fileChooser = new FileChooser();
        finding = fileChooser.showOpenDialog(stage);
    }

    public void save() {
        if(checkSelectedDog() && checkFinding()) {
            displayAlertBox("Pas je uspješno pregledan!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkFinding() {
        if(finding == null) {
            displayAlertBox("Nije izabran nalaz!");
            return false;
        }
        return true;
    }

    private boolean checkSelectedDog() {
        if(dogsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            displayAlertBox("Nije izabran pas za pregled!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
