package GUI.preview.dogs_preview;

import GUI.adding_dog.AddingDogForm;
import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class DogsPreviewController {

    private Stage stage;
    private List<DogDTO> listOfDogs;

    @FXML
    private ImageView dogImageView;
    @FXML
    private TableView<DogDTO> dogsTableView;



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
        try {

        } catch (Exception ex) {

        }
    }

    public void deleteDog() {
        try {

        } catch (Exception ex) {

        }
    }

    public void search() {

    }

    public void showAll() {

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

}
