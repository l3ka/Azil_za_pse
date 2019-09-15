package GUI.admin.select_account;

import GUI.admin.change_account.ChangeAccount;
import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectAccountController {

    @FXML
    private TableView<EmployeeDTO> accountsTableView;
    @FXML
    private Button selectButton;
    @FXML
    private Button quitButton;

    private Stage stage;

    private List<EmployeeDTO> listOfEmployees = new ArrayList<>();

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            displayEmployees();
            accountsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
            accountsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            accountsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
            accountsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            initButtonEvent();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            selectButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    selectButton.fire();
                    e.consume();
                }
            });
            quitButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    quitButton.fire();
                    e.consume();
                }
            });
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    public void select() {
        try {
            if(checkSelectedEmployee()) {
                new ChangeAccount(accountsTableView.getSelectionModel().getSelectedItem()).display();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - select", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedEmployee() {
        try {
            if(accountsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran zaposleni!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - checkSelectedEmployee", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayEmployees() {
        try {
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartors());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinarians());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servants());
            for(EmployeeDTO employee : listOfEmployees) {
                accountsTableView.getItems().add(employee);
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - displayEmployees", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("SelectAccountController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
