package GUI.employment;

import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeContractDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class EmploymentController {

    @FXML
    private TableView<EmployeeContractDTO> employmentTableView;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnJMB;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnName;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnSurname;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnAddress;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnPhone;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnSalary;
    @FXML
    private TableColumn<EmployeeContractDTO, String> columnPosition;

    private Stage stage;
    private List<EmployeeContractDTO> listOfEmployments;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            /*employmentTableView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("from"));
            employmentTableView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("to"));
            columnJMB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getJMB()));
            columnName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getName()));
            columnSurname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getSurname()));
            columnAddress.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getResidenceAddress()));
            columnPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getTelephoneNumber()));
            columnSalary.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getEmploymentContract().getSalary())));
            columnPosition.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmploymentContract().getPosition()));

            displayEmployments();*/
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    public void editEmployment() {
        try {
            if(checkSelectedEmployment()) {

            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - editEmployment", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllEmployments() {
        try {
            displayEmployments();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - displayAllEmployments", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedEmployment() {
        try {
            if(employmentTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran zaposleni i njegov ugovor!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - checkSelectedEmployment", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayEmployments() {
        try {
            employmentTableView.getItems().clear();
            employmentTableView.refresh();
            listOfEmployments = AzilUtilities.getDAOFactory().getEmployeeContractDAO().getAllEmployeeContract();
            for(EmployeeContractDTO employeeContract : listOfEmployments) {
                employmentTableView.getItems().add(employeeContract);
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - displayEmployments", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
