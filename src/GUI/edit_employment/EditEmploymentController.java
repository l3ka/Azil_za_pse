package GUI.edit_employment;

import data.dto.EmployeeContractDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class EditEmploymentController {

    private Stage stage;
    private EmployeeContractDTO employeeContract;
    @FXML
    private TextField id, name, surname,address, phone, salary, position;
    @FXML
    private DatePicker contractTo, contractFrom;

    public void initialize(Stage stage, EmployeeContractDTO employeeContract) {
        try {
            this.stage = stage;
            this.employeeContract = employeeContract;
            id.setText(employeeContract.getJmbEmployee());
            name.setText(AzilUtilities.getDAOFactory().getEmployeeDAO().getEmployeeByJMB(employeeContract.getJmbEmployee()).getName());
            surname.setText(AzilUtilities.getDAOFactory().getEmployeeDAO().getEmployeeByJMB(employeeContract.getJmbEmployee()).getSurname());
            address.setText(AzilUtilities.getDAOFactory().getEmployeeDAO().getEmployeeByJMB(employeeContract.getJmbEmployee()).getResidenceAddress());
            phone.setText(AzilUtilities.getDAOFactory().getEmployeeDAO().getEmployeeByJMB(employeeContract.getJmbEmployee()).getTelephoneNumber());
            salary.setText(Double.toString(AzilUtilities.getDAOFactory().getEmploymentContractDAO().getById(employeeContract.getIdEmploymentContract()).getSalary()));
            position.setText(AzilUtilities.getDAOFactory().getEmploymentContractDAO().getById(employeeContract.getIdEmploymentContract()).getPosition());
            contractFrom.setValue(employeeContract.getFrom().toLocalDate());
            contractTo.setValue(LocalDate.now());
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditEmploymentController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void edit() {
        try {
            employeeContract.setTo(Date.valueOf(contractTo.getValue()));
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditEmploymentController - edit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditEmploymentController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
