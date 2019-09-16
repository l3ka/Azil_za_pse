package GUI.employment;

import GUI.alert_box.AlertBoxForm;
import data.dto.EmployeeContractDTO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmploymentController {

    @FXML
    private TableView<EmployeeDTO> employeesTableView;
    @FXML
    private Label positionLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label dateFromLabel;


    private Stage stage;
    private List<EmployeeDTO> listOfEmployees = new ArrayList<>();
    private List<EmployeeContractDTO> listOfEmployments;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            employeesTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
            employeesTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            employeesTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
            employeesTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
            employeesTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            employeesTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qualifications"));

            displayEmployees();
            listOfEmployments = AzilUtilities.getDAOFactory().getEmployeeContractDAO().getAllEmployeeContract();

            employeesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                int contractId = listOfEmployments.stream().
                        filter(employeeContractDTO -> employeeContractDTO.getJmbEmployee().equals(newValue.getJMB()) && employeeContractDTO.getTo() == null).
                        findAny().get().getIdEmploymentContract();
                EmploymentContractDTO employmentContract = AzilUtilities.getDAOFactory().getEmploymentContractDAO().getById(contractId);
                positionLabel.setText(employmentContract.getPosition());
                salaryLabel.setText("" + employmentContract.getSalary());
                for(EmployeeContractDTO employeeContract : listOfEmployments) {
                    if(employeeContract.getIdEmploymentContract() == contractId) {
                        dateFromLabel.setText(employeeContract.getFrom().toString());
                    }
                }
            });
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayEmployees() {
        try {
            employeesTableView.getItems().clear();
            employeesTableView.refresh();

            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartors());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getServantDAO().servants());
            listOfEmployees.addAll(AzilUtilities.getDAOFactory().getVeterinarinaDAO().veterinarians());

            for(EmployeeDTO employee : listOfEmployees) {
                employeesTableView.getItems().add(employee);
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EmploymentController - displayEmployees", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
