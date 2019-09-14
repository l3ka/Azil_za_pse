package GUI.employee.main_screen;

import GUI.adopting_dog.AdoptingDogForm;
import GUI.login.LoginForm;
import GUI.preview.cages_preview.CagesForm;
import GUI.preview.dogs_preview.DogsPreviewForm;
import GUI.preview.foster_parents_preview.FosterParentsForm;
import GUI.preview.medicine_preview.MedicinePreviewForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class MainScreenController {

    @FXML
    private Label loggedUserLabel;

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

    public void logOut() {
        stage.close();
        try {
            new LoginForm().start(new Stage());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayDogs() {
        try {
            new DogsPreviewForm().display();
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
        try {
            new MedicinePreviewForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayCages() {
        try {
            new CagesForm().display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void adopting() {
        try {
            new AdoptingDogForm().display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
