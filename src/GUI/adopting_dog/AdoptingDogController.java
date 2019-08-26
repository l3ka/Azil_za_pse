package GUI.adopting_dog;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdoptingDogController {

    @FXML private Button adoptDogButton;
    @FXML private TableView<String> dogsTableView;
    @FXML private TableView<String> fosterParentsTableView;

    @FXML
    private void initialize() {

    }

    public void adoptDog() {
        if(dogsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            try {
                new AlertBoxForm("Nije izabran pas!").display();
            } catch(Exception exception) {

            }
        } else if(fosterParentsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            try {
                new AlertBoxForm("Nije izabran udomitelj!").display();
            } catch(Exception exception) {

            }
        } else {
            try {
                new AlertBoxForm("Pas je uspješno udomljen!").display();
            } catch(Exception exception) {

            }
            dogsTableView.getSelectionModel().clearSelection();
            fosterParentsTableView.getSelectionModel().clearSelection();
        }
    }

    public void quit() {
        Stage stage = (Stage) adoptDogButton.getScene().getWindow();
        stage.close();
    }
}
