package GUI.login;

import GUI.admin.main.AdminMainForm;
import GUI.alert_box.AlertBoxForm;
import GUI.employee.main_screen.MainScreen;
import GUI.vet.main.VetMainForm;
import data.dto.AdministratorDTO;
import data.dto.EmployeeDTO;
import data.dto.ServantDTO;
import data.dto.VeterinarianDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;

public class LoginController {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void logIn() {
        if (checkCredentials()) {
            try {
                new AlertBoxForm("Credentials are not in valid form!").display();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            try {
                EmployeeDTO employee = null;
                String username = usernameTextField.getText().trim();
                String password = passwordField.getText().trim();

                if (AzilUtilities.getDAOFactory().getAdministratorDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getAdministratorDAO().login(username, password);
                    if (employee != null) {
                        new AdminMainForm().display(employee);
                    }
                    // changeStage(event, ".." + File.separatorChar + "admin" + File.separatorChar + "main" + File.separatorChar + "mainFormAdmin.fxml");
                } else if (AzilUtilities.getDAOFactory().getVeterinarinaDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getVeterinarinaDAO().login(username, password);
                    if (employee != null) {
                        new VetMainForm().display(employee);
                    }
                    // changeStage(event, ".." + File.separatorChar + "vet" + File.separatorChar + "main" + File.separatorChar + "mainFormVet.fxml");
                } else if (AzilUtilities.getDAOFactory().getServantDAO().exists(username, password)) {
                    employee = AzilUtilities.getDAOFactory().getServantDAO().login(username, password);
                    if (employee != null) {
                        new MainScreen().display(employee);
                    }
                    // changeStage(event, ".." + File.separatorChar + "employee" + File.separatorChar + "main_screen" + File.separatorChar + "MainScreen.fxml");
                }
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean checkCredentials() {
        return usernameTextField.toString().trim().isEmpty() || passwordField.toString().trim().isEmpty() || passwordField.toString().trim().startsWith("'");
    }

    public void changeStage(ActionEvent event, String path) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
