package GUI.login;

import GUI.admin.main.AdminMainForm;
import GUI.alert_box.AlertBoxForm;
import GUI.employee.main_screen.MainScreen;
import GUI.vet.main.VetMainForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class LoginController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private Stage stage;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            loginButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    loginButton.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void logIn() {
        try {
            if (checkCredentials()) {
                new AlertBoxForm("Korisničko ime ili lozinka nisu ispravno uneseni!").display();
                clearInputFields();
            }
            else {
                EmployeeDTO employee = null;
                String username = usernameTextField.getText().trim();
                String password = passwordField.getText().trim();
                if (AzilUtilities.getDAOFactory().getAdministratorDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getAdministratorDAO().login(username, password);
                    if (employee != null) {
                        new AdminMainForm(employee).display();
                    } else {
                        new AlertBoxForm("Izabrani korisnik vise nije aktivan!").display();
                        clearInputFields();
                        return;
                    }
                } else if (AzilUtilities.getDAOFactory().getVeterinarinaDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getVeterinarinaDAO().login(username, password);
                    if (employee != null) {
                        new VetMainForm(employee).display();
                    } else {
                        new AlertBoxForm("Izabrani korisnik vise nije aktivan!").display();
                        clearInputFields();
                        return;
                    }
                } else if (AzilUtilities.getDAOFactory().getServantDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getServantDAO().login(username, password);
                    if (employee != null) {
                        new MainScreen(employee).display();
                    } else {
                        new AlertBoxForm("Izabrani korisnik vise nije aktivan!").display();
                        clearInputFields();
                        return;
                    }
                } else {
                    new AlertBoxForm("Neispravno korisničko ime ili lozinka!").display();
                    clearInputFields();
                    return;
                }
                quit();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - logIn", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void close() {
        stage.close();
    }

    public boolean checkCredentials() {
        try {
            return usernameTextField.toString().trim().isEmpty() || passwordField.toString().trim().isEmpty() || passwordField.toString().trim().startsWith("'");
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    public void changeStage(ActionEvent event, String path) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - changeStage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void clearInputFields() {
        try {
            usernameTextField.clear();
            passwordField.clear();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("LoginController - clearInputFields", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
