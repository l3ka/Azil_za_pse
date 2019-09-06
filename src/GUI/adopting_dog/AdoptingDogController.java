package GUI.adopting_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.AdoptingDTO;
import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class AdoptingDogController {

    @FXML private TableView<DogDTO> dogsTableView;
    @FXML private TableView<FosterParentDTO> fosterParentsTableView;
    private List<DogDTO> listOfDogs;
    private List<FosterParentDTO> listOfFosterParents;
    private DogDTO selectedDog;
    private FosterParentDTO selectedFosterParent;
    private AdoptingDTO adoptingDTO;
    private Stage stage;

    @FXML
    public void initialize(Stage stage) {
        this.stage = stage;

        dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
        dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
        dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
        dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("surname"));
        fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        displayDogs();
        displayFosterParents();
    }

    public void adoptDog() {
        if(checkDog() && checkFosterParent()) {
            selectedDog = dogsTableView.getSelectionModel().getSelectedItem();
            selectedFosterParent = fosterParentsTableView.getSelectionModel().getSelectedItem();

            selectedDog.setAdopted(true);
            AzilUtilities.getDAOFactory().getDogDAO().update(selectedDog);
            adoptingDTO = new AdoptingDTO(selectedDog, new Date(Calendar.getInstance().getTime().getTime()), null);
            selectedFosterParent.addDog(adoptingDTO);

            AzilUtilities.getDAOFactory().getAdoptingDAO().insert(selectedFosterParent, adoptingDTO);
            displayAlertBox("Pas je uspješno udomljen!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkDog() {
        if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran pas!");
            return false;
        }
        return true;
    }

    private boolean checkFosterParent() {
        if(fosterParentsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran udomitelj!");
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

    private void displayDogs() {
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for (DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }

    private void displayFosterParents() {
        listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents();
        for(FosterParentDTO fosterParent : listOfFosterParents) {
            fosterParentsTableView.getItems().add(fosterParent);
        }
    }
}
