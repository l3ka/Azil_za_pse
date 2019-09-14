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
        this.stage = stage;

        accountsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
        accountsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        accountsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        accountsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qualifications"));
        accountsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        accountsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        displayEmployees();
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
        if(checkName()) {

        }
    }

    public void displayAllAccounts() {
        displayEmployees();
    }

    public void quit() {
        stage.close();
    }

    private void displayEmployees() {
        accountsTableView.getItems().clear();
        accountsTableView.refresh();
        listOfEmployees.clear();
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartorsDeactivated());
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinariansDeactivated());
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servantsDeactivated());
        numberOfEmployees.setText("Broj zaposlenih: " + listOfEmployees.size());
        for(EmployeeDTO employee : listOfEmployees) {
            accountsTableView.getItems().add(employee);
        }
    }

    private boolean checkSelectedAccount() {
        if(accountsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran nalog!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DeactivatedAccountsController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            displayAlertBox("Nije uneseno ime za pretragu!");
            return false;
        }
        return true;
    }
}
