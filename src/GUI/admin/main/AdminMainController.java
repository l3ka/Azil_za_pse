package GUI.admin.main;

import GUI.adding_dog.AddingDogForm;
import GUI.adding_medicine.AddingMedicineForm;
import GUI.admin.add_change_account.AddAndChangeAccount;
import GUI.adopting_dog.AdoptingDogForm;
import GUI.admin.generating_statistic.GeneratingStatisticForm;
import GUI.login.LoginForm;
import data.dto.DogDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class AdminMainController {
    @FXML private Button addDogButton;
    @FXML private TableView<DogDTO> dogsTableView;
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

    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
        } catch(Exception exception) {

        }
    }

    public void addMedicine() {
        try {
            new AddingMedicineForm().display();
        } catch(Exception exception) {

        }
    }

    public void addAccount() {
        try {
            new AddAndChangeAccount().display();
        } catch (Exception exception) {

        }
    }

    public void editAccount() {
        try {
            new AddAndChangeAccount().display();
        } catch (Exception exception) {

        }
    }

    public void generateStatistic() {
        try {
            new GeneratingStatisticForm().display();
        } catch(Exception exception) {

        }
    }

    public void logOut() {
        stage.close();

        try {
            new LoginForm().start(new Stage());
        } catch(Exception exception) {

        }
    }
}
