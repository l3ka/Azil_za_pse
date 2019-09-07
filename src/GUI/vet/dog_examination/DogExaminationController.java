package GUI.vet.dog_examination;

import GUI.alert_box.AlertBoxForm;
import GUI.vet.generating_finding.GeneratingFindingForm;
import data.dto.DogDTO;
import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class DogExaminationController {
    @FXML TableView<DogDTO> medicalResultTableView;
    private List<DogDTO> listOfDogs;
    private Stage stage;
    private EmployeeDTO employee;
    private DogDTO dog;

    public void initialize(Stage stage, DogDTO dog, EmployeeDTO employee) {
        this.stage = stage;
        this.dog = dog;
        this.employee = employee;

    }

    public void save() {
            stage.close();
        }

    public void quit() {
        stage.close();
    }

    public void generateFinding() {
        try {
                new GeneratingFindingForm(dog, employee).display();
            } catch(Exception exception) {

        }
    }



    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
