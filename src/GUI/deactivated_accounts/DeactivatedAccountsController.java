package GUI.deactivated_accounts;

import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class DeactivatedAccountsController {

    @FXML
    private TableView<EmployeeDTO> accountsTableView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label numberOfEmployees;

    private Stage stage;
    private List<EmployeeDTO> listOfEmployees = new ArrayList<>();


    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            accountsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
            accountsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            accountsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
            accountsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qualifications"));
            accountsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
            accountsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

            displayEmployees();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void activateAccount() {
        try {
            if(checkSelectedAccount()) {
                if (AzilUtilities.getDAOFactory().getEmployeeDAO().activate(accountsTableView.getSelectionModel().getSelectedItem())) {
                    displayAlertBox("Nalog je uspjesno aktiviran!");
                }
                else {
                    displayAlertBox("Desila se greska prilikom aktiviranja naloga!");
                }
                displayEmployees();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - activateAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            String inputText = nameTextField.getText().toUpperCase();
            List<EmployeeDTO> filteredList = listOfEmployees.stream().filter(employeeDTO -> employeeDTO.getName().toUpperCase().
                    contains(inputText)).collect(Collectors.toList());
            accountsTableView.getItems().clear();
            for (EmployeeDTO employee : filteredList) {
                accountsTableView.getItems().add(employee);
            }
            accountsTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllAccounts() {
        try {
            displayEmployees();
            nameTextField.clear();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - displayAllAccounts", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayEmployees() {
        try {
            accountsTableView.getItems().clear();
            accountsTableView.refresh();
            listOfEmployees.clear();
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartorsDeactivated());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinariansDeactivated());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servantsDeactivated());
            numberOfEmployees.setText("Broj deaktiviranih naloga: " + listOfEmployees.size());
            for(EmployeeDTO employee : listOfEmployees) {
                accountsTableView.getItems().add(employee);
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - displayEmployees", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedAccount() {
        try {
            if(accountsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran nalog!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - checkSelectedAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
