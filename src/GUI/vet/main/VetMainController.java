package GUI.vet.main;

import GUI.login.LoginForm;
import GUI.vet.dog_examination.DogExaminationForm;
import GUI.vet.taking_medicine.TakingMedicineForm;
import GUI.vet.generating_finding.GeneratingFindingForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VetMainController {
    @FXML private Button logOutButton;
    @FXML private TableView<String> dogsTableView;

    @FXML
    private void initialize() {

    }

    public void examineDog() {
        try {
            new DogExaminationForm().display();
        } catch(Exception exception) {

        }
    }

    public void takeDrug() {
        try {
            new TakingMedicineForm().display();
        } catch(Exception exception) {

        }
    }

    public void generateFinding() {
        try {
            new GeneratingFindingForm().display();
        } catch(Exception exception) {

        }
    }

    public void logOut() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();

        try {
            new LoginForm().start(new Stage());
        } catch(Exception exception) {

        }
    }
}
