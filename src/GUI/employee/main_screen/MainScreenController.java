package GUI.employee.main_screen;

import GUI.adding_dog.AddingDogForm;
import GUI.adding_medicine.AddingMedicineForm;
import GUI.adopting_dog.AdoptingDogForm;
import GUI.login.LoginForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label loggedUserLabel;

    private List<DogDTO> listOfDogs;
    private Stage stage;
    private EmployeeDTO employee;

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
        loggedUserLabel.setText("Prijavljeni korisnik: " + employee.getUsername());
    }


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
        } catch(Exception exception) {

        }
    }

    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addMedicine() {
        try {
            new AddingMedicineForm().display();
        } catch(Exception ex) {

        }
    }

    public void logOut() {
        stage.close();

        try {
            new LoginForm().start(new Stage());
        } catch(Exception exception) {

        }
    }

    private void displayDogs() {
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }
}
