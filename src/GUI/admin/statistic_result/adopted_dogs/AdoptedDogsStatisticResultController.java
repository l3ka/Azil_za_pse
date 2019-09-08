package GUI.admin.statistic_result.adopted_dogs;

import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.time.LocalDate;
import java.util.List;


public class AdoptedDogsStatisticResultController {

    @FXML private Label titleLabel;
    @FXML private TableView<DogDTO> dogsTableView;

    private Stage stage;
    private LocalDate dateFrom;
    private List<DogDTO> listOfDogs;

    public void initialize(Stage stage, LocalDate dateFrom) {
        this.stage = stage;
        this.dateFrom = dateFrom;

        titleLabel.setText("Udomljeni psi od " + dateFrom);

        dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
        dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
        dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
        dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        displayDogs();
    }

    public void OKButtonPressed() {
        stage.close();
    }

    private void displayDogs() {
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().getAdoptedDogs(dateFrom);
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }
}
