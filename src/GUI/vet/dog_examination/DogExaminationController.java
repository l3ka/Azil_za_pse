package GUI.vet.dog_examination;

import GUI.alert_box.AlertBoxForm;
import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.util.List;

public class DogExaminationController {
    @FXML TableView<DogDTO> dogsTableView;
    private List<DogDTO> listOfDogs;
    private File finding;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
        dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
        dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
        dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
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
