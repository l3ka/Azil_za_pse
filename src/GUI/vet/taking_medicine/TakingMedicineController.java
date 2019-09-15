package GUI.vet.taking_medicine;

import GUI.alert_box.AlertBoxForm;
import GUI.vet.medicine_quantity.MedicineQuantityForm;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import data.dto.MedicalResultDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class TakingMedicineController {

    @FXML
    private TableView<MedicineDTO> drugsTableView;
    @FXML
    private Button takeMedicineButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private List<MedicineDTO> listOfMedicines;
    private EmployeeDTO employee;
    private MedicalResultDTO medicalResult;

    public void initialize(Stage stage, EmployeeDTO employee, MedicalResultDTO medicalResult) {
        try {
            this.stage = stage;
            this.employee = employee;
            this.medicalResult = medicalResult;
            drugsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            drugsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantity"));
            drugsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));

            displayMedicines();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - initialize" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            takeMedicineButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    takeMedicineButton.fire();
                    e.consume();
                }
            });
            quitButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    quitButton.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - initButtonEvent" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void takeMedicine() {
        try {
            if(checkSelectedDrug()) {
                new MedicineQuantityForm(drugsTableView.getSelectionModel().getSelectedItem(), employee, medicalResult).display();
                drugsTableView.getItems().clear();
                displayMedicines();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - takeMedicine" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedDrug() {
        try {
            if(drugsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
                displayAlertBox("Nije izabran lijek!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - checkSelectedDrug" , new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayMedicines() {
        try {
            listOfMedicines = AzilUtilities.getDAOFactory().getMedicineDAO().medicines();
            for(MedicineDTO medicine : listOfMedicines) {
                drugsTableView.getItems().add(medicine);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - displayMedicines", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> TakingMedicineController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
