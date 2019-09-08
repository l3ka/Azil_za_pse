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
        this.stage = stage;
        this.dog = dog;
        this.employee = employee;

        medicalResultsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        medicalResultsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("veterinarianJMB"));
        medicalResultsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("resultsAndOpinion"));

        displayMedicalResults();
        initButtonEvent();
    }

    private void initButtonEvent() {
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
    }

    public void quit() {
        stage.close();
    }

    public void generateFinding() {
        try {
            new GeneratingFindingForm(dog, employee).display();
            displayMedicalResults();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), ex.fillInStackTrace().toString()));
        }
    }

    public void takeMedicine() {
        if (checkMedicalResult()) {
            try {
                new TakingMedicineForm(employee, medicalResultsTableView.getSelectionModel().getSelectedItem()).display();
            } catch (Exception ex) {
                AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), ex.fillInStackTrace().toString()));
            }
        }
    }

    private void displayMedicalResults() {
        medicalResultsTableView.getItems().clear();
        medicalResultsTableView.refresh();
        listOfMedicalResults = AzilUtilities.getDAOFactory().getMedicalResultDAO().medicalResults(dog);
        for(MedicalResultDTO medicalResult : listOfMedicalResults) {
            medicalResultsTableView.getItems().add(medicalResult);
        }
    }

    private boolean checkMedicalResult() {
        if (medicalResultsTableView.getSelectionModel().getSelectedItem() != null) {
            return true;
        }
        else {
            try {
                new AlertBoxForm("Nije izabran nalaz!").display();
            }
            catch (Exception ex) {
                AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO(employee.getUsername(), ex.fillInStackTrace().toString()));
            }
            return false;
        }
    }

}
