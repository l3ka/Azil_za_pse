package GUI.admin.select_account;

import GUI.admin.change_account.ChangeAccount;
import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;
import java.util.ArrayList;
import java.util.List;

public class SelectAccountController {

    private Stage stage;
    @FXML
    private TableView<EmployeeDTO> accountsTableView;
    @FXML
    private Button selectButton;
    @FXML
    private Button quitButton;

    private List<EmployeeDTO> listOfEmployees = new ArrayList<>();

    public void initialize(Stage stage) {
        this.stage = stage;
        displayEmployees();
        accountsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        accountsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        accountsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        accountsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        initButtonEvent();
    }

    private void initButtonEvent() {
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
    }


    public void select() {
        if(checkSelectedEmployee()) {
            try {
                new ChangeAccount(accountsTableView.getSelectionModel().getSelectedItem()).display();
            } catch(Exception ex) {

            }
        }
    }

    public void quit() {
     stage.close();
    }

    private boolean checkSelectedEmployee() {
        if(accountsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran zaposleni!");
            return false;
        }
        return true;
    }

    private void displayEmployees() {
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartors());
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinarians());
        listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servants());
        for(EmployeeDTO employee : listOfEmployees) {
            accountsTableView.getItems().add(employee);
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

}
