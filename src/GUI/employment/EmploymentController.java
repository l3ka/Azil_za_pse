package GUI.employment;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EmploymentController {

    @FXML
    private TableView employmentTableView;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }


    public void editEmployment() {
        if(checkSelectedEmployment()) {

        }
    }

    public void displayAllEmployments() {
        displayEmployments();
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedEmployment() {
        if(employmentTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran zaposleni i njegov ugovor!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

    private void displayEmployments() {

    }
}
