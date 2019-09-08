package GUI.vet.dog_examination;

import GUI.alert_box.AlertBoxForm;
import GUI.vet.generating_finding.GeneratingFindingForm;
import GUI.vet.taking_medicine.TakingMedicineForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import data.dto.MedicalResultDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class DogExaminationController {
    @FXML private TableView<MedicalResultDTO> medicalResultsTableView;
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
    }

    public void quit() {
        stage.close();
    }

    public void generateFinding() {
        try {
            new GeneratingFindingForm(dog, employee).display();
            displayMedicalResults();
        } catch (Exception exception) {

        }
    }

    public void takeMedicine() {
        if (checkMedicalResult()) {
            try {
                new TakingMedicineForm(employee, medicalResultsTableView.getSelectionModel().getSelectedItem()).display();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void displayMedicalResults() {
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
            catch (Exception exception) {

            }
            return false;
        }
    }
}
