package GUI.vet.main;

import GUI.alert_box.AlertBoxForm;
import GUI.login.LoginForm;
import GUI.vet.dog_examination.DogExaminationForm;
import GUI.vet.taking_medicine.TakingMedicineForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.VeterinarianDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class VetMainController {
    @FXML
    private Button logOutButton;
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

        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }

    public void examineDog() {
        if (checkSelectedDog()) {
            try {
                new DogExaminationForm(dogsTableView.getSelectionModel().getSelectedItem(),employee).display();
            } catch (Exception exception) {
                exception.printStackTrace();
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

    private boolean checkSelectedDog() {
        if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Niste izabrali psa za pregled!");
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
