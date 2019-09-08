package GUI.employee.main_screen;

import GUI.add_foster_parent.AddFosterParent;
import GUI.adding_cage.AddingCageForm;
import GUI.adding_dog.AddingDogForm;
import GUI.adding_medicine.AddingMedicineForm;
import GUI.adopting_dog.AdoptingDogForm;
import GUI.alert_box.AlertBoxForm;
import GUI.login.LoginForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Button logOutButton;
    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private Label loggedUserLabel;
    @FXML
    private TextField searchParametarTextField;


    private List<DogDTO> listOfDogs;
    private Stage stage;
    private EmployeeDTO employee;
    private List<DogDTO> listOfSearchedDogs;

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
        initButtonEvent();
    }

    private void initButtonEvent() {
        logOutButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                logOutButton.fire();
                e.consume();
            }
        });
    }

    public void addDog() {
        try {
            new AddingDogForm().display();
        } catch(Exception exception) {

        }
        displayDogs();
    }

    public void addFosterParent() {
        try {
            new AddFosterParent().display();
        } catch (Exception exception) {
            
        }
    }

    public void addCage() {
        try {
            new AddingCageForm().display();
        } catch(Exception ex) {

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

    public void searchDog() {
        if(checkSearchParameter()) {
            dogsTableView.getItems().clear();
            dogsTableView.refresh();
            listOfSearchedDogs = AzilUtilities.getDAOFactory().getDogDAO().dogsByBreed(searchParametarTextField.getText().trim());
            for(DogDTO dog : listOfSearchedDogs) {
                dogsTableView.getItems().add(dog);
            }
        }
    }

    public void logOut() {
        stage.close();
        try {
            new LoginForm().start(new Stage());
        } catch(Exception exception) {

        }
    }

    public void displayDogs() {
        dogsTableView.getItems().clear();
        dogsTableView.refresh();
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

    private boolean checkSearchParameter() {
        if("".equals(searchParametarTextField.getText().trim())) {
            displayAlertBox("Niste unijeli naziv na pretragu!");
            return false;
        }
        return true;
    }

}
