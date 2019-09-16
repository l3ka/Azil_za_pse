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
import java.util.stream.Collectors;

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
    private TextField searchTextField;

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
        try {
            this.stage = stage;
            dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
            dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
            dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
            dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

            displayDogs();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - initialize" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
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
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - initButtonEvent" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    public void examineDog() {
        try {
            if (checkSelectedDog()) {
                new DogExaminationForm(dogsTableView.getSelectionModel().getSelectedItem(),employee).display();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - examineDog" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void searchDog() {
        try {
            String inputText = searchTextField.getText().toUpperCase();

            List<DogDTO> filteredList = listOfDogs.stream().filter((dog -> dog.getBreed().toUpperCase().contains(inputText))).collect(Collectors.toList());
            dogsTableView.getItems().clear();
            for (DogDTO dog : filteredList) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();

        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - searchDog" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllDogs() {
        try {
            if (searchTextField.getText() == null) return;
            dogsTableView.getItems().clear();
            for (DogDTO dog : listOfDogs) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
            searchTextField.clear();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - searchDog" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayDogs() {
        try {
            dogsTableView.getItems().clear();
            dogsTableView.refresh();
            listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
            for(DogDTO dog : listOfDogs) {
                dogsTableView.getItems().add(dog);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - displayDogs" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void logOut() {
        try {
            quit();
            new LoginForm().start(new Stage());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - logOut", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedDog() {
        try {
            if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Niste izabrali psa za pregled!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - checkSelectedDog" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }


    private void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> VetMainController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
