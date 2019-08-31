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
            try {
                new AlertBoxForm("Pas je uspješno pregledan!").display();
            } catch (Exception exception) {

            }

            finding = null;
            dogsTableView.getSelectionModel().clearSelection();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkFinding() {
        if(finding == null) {
            try {
                new AlertBoxForm("Nije izabran nalaz!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkSelectedDog() {
        if(dogsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            try {
                new AlertBoxForm("Nije izabran pas za pregled!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }
}
