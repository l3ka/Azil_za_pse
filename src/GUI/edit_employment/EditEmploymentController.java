package GUI.edit_employment;

import data.dto.EmployeeContractDTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.time.LocalDate;

public class EditEmploymentController {

    private Stage stage;
    private EmployeeContractDTO employeeContract;
    @FXML
    private TextField id, name, surname,address, phone, salary, position;
    @FXML
    private DatePicker contractTo, contractFrom;

    public void initialize(Stage stage, EmployeeContractDTO employeeContract) {
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
    }

    public void edit() {
        employeeContract.setTo(Date.valueOf(contractTo.getValue()));
    }

    public void quit() {
        stage.close();
    }


}
