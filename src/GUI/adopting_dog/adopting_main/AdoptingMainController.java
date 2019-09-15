package GUI.adopting_dog.adopting_main;

import GUI.adopting_dog.adopt_dog.AdoptingDogForm;
import GUI.alert_box.AlertBoxForm;
import data.dao.AdoptingDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdoptingMainController {
    @FXML
    private TableView<AdoptingDAO> adoptingDogsTableView;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;


    }

    public void updateAdopting() {
        if(checkSelectedAdopting()) {

        }
    }

    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
        } catch(Exception ex) {

        }
    }

    public void displayAllAdoptings() {
        displayAdoptings();
    }

    public void quit() {
        stage.close();
    }

    private void displayAdoptings() {

    }

    private boolean checkSelectedAdopting() {
        if(adoptingDogsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabrano udomljavanje!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }
}
