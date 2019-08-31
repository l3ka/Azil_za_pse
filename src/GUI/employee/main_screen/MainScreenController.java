package GUI.employee.main_screen;

import GUI.adding_dog.AddingDogForm;
import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class MainScreenController {

    @FXML
    private Button addDogButton;
    @FXML
    private Button adoptDogButton;
    @FXML
    private Button addMedicineButton;
    @FXML
    private TableView<DogDTO> dogsTableView;
    private List<DogDTO> listOfDogs;
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

    public void addDog() {
        try {
            new AddingDogForm().display();
        } catch(Exception exception) {

        }
    }

}
