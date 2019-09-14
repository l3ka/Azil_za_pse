package GUI.vet.main;

import GUI.alert_box.AlertBoxForm;
import GUI.login.LoginForm;
import GUI.vet.dog_examination.DogExaminationForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
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

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class VetMainController {

    @FXML
    private Button logOutButton;
    @FXML
    private Button examineDogButton;
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
        examineDogButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                examineDogButton.fire();
                e.consume();
            }
        });
    }


    public void examineDog() {
        if (checkSelectedDog()) {
            try {
                new DogExaminationForm(dogsTableView.getSelectionModel().getSelectedItem(),employee).display();
            } catch (Exception ex) {
                AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            }
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

    public void displayDogs() {
        dogsTableView.getItems().clear();
        dogsTableView.refresh();
        listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
        for(DogDTO dog : listOfDogs) {
            dogsTableView.getItems().add(dog);
        }
    }

    public void logOut() {
        quit();
        try {
            new LoginForm().start(new Stage());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSearchParameter() {
        if("".equals(searchParametarTextField.getText().trim())) {
            displayAlertBox("Niste unijeli naziv na pretragu!");
            return false;
        }
        return true;
    }

    private void quit() {
        stage.close();
    }

}
