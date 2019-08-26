package GUI.admin.main;

import GUI.adding_dog.AddingDogForm;
import GUI.adding_medicine.AddingMedicineForm;
import GUI.adopting_dog.AdoptingDogForm;
import GUI.admin.generating_statistic.GeneratingStatisticForm;
import GUI.login.LoginForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainController {
    @FXML private Button addDogButton;

    @FXML
    private void initialize() {

    }

    public void addDog() {
        try {
            new AddingDogForm().display();
        } catch(Exception exception) {

        }
    }

    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
        } catch(Exception exception) {

        }
    }

    public void addMedicine() {
        try {
            new AddingMedicineForm().display();
        } catch(Exception exception) {

        }
    }

    public void addAccount() {

    }

    public void editAccount() {

    }

    public void generateStatistic() {
        try {
            new GeneratingStatisticForm().display();
        } catch(Exception exception) {

        }
    }

    public void logOut() {
        Stage stage = (Stage) addDogButton.getScene().getWindow();
        stage.close();

        try {
            new LoginForm().start(new Stage());
        } catch(Exception exception) {

        }
    }
}
