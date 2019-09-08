package GUI.admin.main;

import GUI.add_foster_parent.AddFosterParent;
import GUI.adding_cage.AddingCageForm;
import GUI.adding_dog.AddingDogForm;
import GUI.adding_medicine.AddingMedicineForm;
import GUI.admin.add_account.AddAccount;
import GUI.admin.select_account.SelectAccount;
import GUI.adopting_dog.AdoptingDogForm;
import GUI.admin.generating_statistic.GeneratingStatisticForm;
import GUI.alert_box.AlertBoxForm;
import GUI.login.LoginForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class AdminMainController {
    @FXML private TableView<DogDTO> dogsTableView;
    @FXML private Label loggedUserLabel;
    @FXML private TextField searchParametarTextField;

    private List<DogDTO> listOfDogs;
    private List<DogDTO> listOfSearchedDogs;
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
        displayDogs();
    }

    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
        } catch(Exception exception) {

        }
    }

    public void addFosterParent() {
        try {
            new AddFosterParent().display();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addCage() {
        try {
            new AddingCageForm().display();
        } catch(Exception exception) {
            exception.printStackTrace();
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
            new AddAccount().display();
        } catch (Exception exception) {

        }
    }

    public void editAccount() {
        try {
            new SelectAccount().display();
        } catch (Exception exception) {

        }
    }

    public void generateStatistic() {
        try {
            new GeneratingStatisticForm().display();
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

    public void searchDog() {
        if(checkSearchParameter()) {
            dogsTableView.getItems().clear();
            listOfSearchedDogs = AzilUtilities.getDAOFactory().getDogDAO().dogsByBreed(searchParametarTextField.getText());
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

    private boolean checkSearchParameter() {
        if("".equals(searchParametarTextField.getText())) {
            displayAlertBox("Niste unijeli naziv na pretragu!");
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
