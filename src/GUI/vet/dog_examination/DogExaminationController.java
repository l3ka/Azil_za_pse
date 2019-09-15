package GUI.vet.dog_examination;

import GUI.alert_box.AlertBoxForm;
import GUI.vet.generating_finding.GeneratingFindingForm;
import GUI.vet.taking_medicine.TakingMedicineForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.LoggerDTO;
import data.dto.MedicalResultDTO;
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

public class DogExaminationController {

    @FXML
    private TableView<MedicalResultDTO> medicalResultsTableView;
    @FXML
    private Button takeMedicineButton;
    @FXML
    private Button generateFindingButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private EmployeeDTO employee;
    private DogDTO dog;
    private List<MedicalResultDTO> listOfMedicalResults;

    public void initialize(Stage stage, DogDTO dog, EmployeeDTO employee) {
        try {
            this.stage = stage;
            this.dog = dog;
            this.employee = employee;

            medicalResultsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
            medicalResultsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("veterinarianJMB"));
            medicalResultsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("resultsAndOpinion"));

            displayMedicalResults();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
            generateFindingButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    generateFindingButton.fire();
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void generateFinding() {
        try {
            new GeneratingFindingForm(dog, employee).display();
            displayMedicalResults();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - generateFinding", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void takeMedicine() {
        try {
            if (checkMedicalResult()) {
                new TakingMedicineForm(employee, medicalResultsTableView.getSelectionModel().getSelectedItem()).display();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - takeMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayMedicalResults() {
        try {
            medicalResultsTableView.getItems().clear();
            medicalResultsTableView.refresh();
            listOfMedicalResults = AzilUtilities.getDAOFactory().getMedicalResultDAO().medicalResults(dog);
            for(MedicalResultDTO medicalResult : listOfMedicalResults) {
                medicalResultsTableView.getItems().add(medicalResult);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - displayMedicalResults", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkMedicalResult() {
        try {
            if (medicalResultsTableView.getSelectionModel().getSelectedItem() != null) {
                return true;
            }
            else {
                new AlertBoxForm("Nije izabran nalaz!").display();
                return false;
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername() + " --> DogExaminationController - checkMedicalResult", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

}
