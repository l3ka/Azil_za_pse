package GUI.preview.accounts_preview;

import GUI.admin.add_account.AddAccount;
import GUI.admin.change_account.ChangeAccount;
import GUI.alert_box.AlertBoxForm;
import GUI.deactivated_accounts.DeactivatedAccountsForm;
import GUI.decide_box.DecideBox;
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

public class AccountsController {

    @FXML
    private TableView<EmployeeDTO> accountsTableView;
    @FXML
    private TextField searchNameTextField;
    @FXML
    private Label numberOfEmployees;

    private Stage stage;
    private EmployeeDTO employee;
    private List<EmployeeDTO> listOfEmployees = new ArrayList<>();

    public void setEmployee(EmployeeDTO employee) { this.employee = employee; }


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
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addAccount() {
        try {
            searchNameTextField.clear();
            new AddAccount().display();
            displayEmployees();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - addAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void updateAccount() {
        try {
            if (checkSelectedAccount()) {
                new ChangeAccount(accountsTableView.getSelectionModel().getSelectedItem()).display();
                displayEmployees();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - updateAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deactivateAccount() {
        try {
            if(checkSelectedAccount()) {
                searchNameTextField.clear();
                boolean choice = new DecideBox("Da li ste sigurni da želite da deaktivirate korisnički nalog?").display();
                if (choice)
                {
                    if (AzilUtilities.getDAOFactory().getEmployeeDAO().delete(accountsTableView.getSelectionModel().getSelectedItem())) {
                        displayAlertBox("Nalog je uspješno deaktiviran!");
                    } else {
                        displayAlertBox("Desila se greška prilikom deaktiviranja naloga!");
                    }
                    displayEmployees();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - deactivateAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayDeactivatedAccounts() {
        try {
            searchNameTextField.clear();
            new DeactivatedAccountsForm().display();
            displayEmployees();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - displayDeactivatedAccounts", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            List<EmployeeDTO> filteredList = listOfEmployees.stream()
                                                            .filter(employeeDTO -> employeeDTO.getName().toUpperCase().contains(searchNameTextField.getText().toUpperCase()))
                                                            .collect(Collectors.toList());
            accountsTableView.getItems().clear();
            for (EmployeeDTO employee : filteredList) {
                accountsTableView.getItems().add(employee);
            }
            accountsTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllAccounts() {
        try {
            accountsTableView.getItems().clear();
            for (EmployeeDTO employee : listOfEmployees) {
                accountsTableView.getItems().add(employee);
            }
            accountsTableView.refresh();
            searchNameTextField.clear();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - displayAllAccounts", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayEmployees() {
        try {
            accountsTableView.getItems().clear();
            accountsTableView.refresh();
            listOfEmployees.clear();
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartors());
            int i = 0;
            for (; i < listOfEmployees.size(); ++i) {
                EmployeeDTO employeeDTO = listOfEmployees.get(i);
                if (employeeDTO.getUsername().equals(employee.getUsername())) {
                    listOfEmployees.remove(i);
                }
            }
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinarians());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servants());
            numberOfEmployees.setText("Broj zaposlenih: " + listOfEmployees.size());
            for(EmployeeDTO employee : listOfEmployees) {
                accountsTableView.getItems().add(employee);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - displayEmployees", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedAccount() {
        try {
            if(accountsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran nalog!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - checkSelectedAccount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return  false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AccountsController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
