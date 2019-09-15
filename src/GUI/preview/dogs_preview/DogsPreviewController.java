package GUI.preview.dogs_preview;

import GUI.adding_dog.AddingDogForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_dog.EditingDogForm;
import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class DogsPreviewController {

    private Stage stage;
    private List<DogDTO> listOfDogs;

    @FXML
    private ImageView dogImageView;
    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private TextField searchDogsTextField;

    public void initialize(Stage stage) {
        this.stage = stage;
        dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
        dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
        dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
        dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        displayDogs();
    }

    public void addDog() {
        try {
            new AddingDogForm().display();
        } catch (Exception ex) {

        }
    }

    public void editDog() {
        if(checkSelectedDog()) {
            try {
                new EditingDogForm(dogsTableView.getSelectionModel().getSelectedItem()).display();
            } catch (Exception ex) {

            }
        }
    }

    public void deleteDog() {
        try {
            if (checkSelectedDog()) {
                boolean choice = new DecideBox("Da li ste sigurni da želite da obrišete psa?").display();
                if (choice) {
                    if (AzilUtilities.getDAOFactory().getDogDAO().delete(dogsTableView.getSelectionModel().getSelectedItem())) {
                       displayAlertBox("Pas je uspješno obrisan!");
                    }
                    else {
                        displayAlertBox("Desila se greška prilikom brisanja");
                    }
                    displayDogs();
                }
            }
        } catch (Exception ex) {

        }
    }

    public void search() {
        try {
            String inputText = searchDogsTextField.getText().toUpperCase();

            List<DogDTO> filteredList = listOfDogs.stream().filter((dog -> dog.getBreed().toUpperCase().contains(inputText))).collect(Collectors.toList());
            dogsTableView.getItems().clear();
            for (DogDTO dog : filteredList) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
        } catch (Exception ex) {

        }
    }

    public void showAll() {
        try {
            if (searchDogsTextField.getText() == null) return;
            dogsTableView.getItems().clear();
            for (DogDTO dog : listOfDogs) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
            searchDogsTextField.clear();
        } catch (Exception ex) {

        }
    }

    public void close() {
        this.stage.close();
    }

    public void displayDogs() {
        dogsTableView.getItems().clear();
        dogsTableView.refresh();
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }

    private boolean checkSelectedDog() {
        if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran pas!");
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
