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
import GUI.preview.accounts_preview.AccountsForm;
import GUI.preview.cages_preview.CagesForm;
import GUI.preview.foster_parents_preview.FosterParentsForm;
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

public class AdminMainController {

    @FXML
    private Label loggedUserLabel;
    @FXML
    private Button logOutButton;

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
    }


    public void generateStatistic() {
        try {
            new GeneratingStatisticForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayDogs() {

    }

    public void displayCages() {
        try {
            new CagesForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayEmployees() {
        try {
            new AccountsForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayFosterParents() {
        try {
            new FosterParentsForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayMedicines() {

    }

    public void logOut() {
        stage.close();
        try {
            new LoginForm().start(new Stage());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }
}
