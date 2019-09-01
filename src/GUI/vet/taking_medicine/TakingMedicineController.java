package GUI.vet.taking_medicine;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TakingMedicineController {

    @FXML private TableView<String> drugsTableView;
    @FXML private ComboBox<Integer> amountComboBox;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        amountComboBox.getItems().addAll(1, 2, 3, 4, 5); // NA OSNOVU BAZE SE MORA POPUNITI!!!
    }

    public void amountChoosed() {

    }

    public void takeMedicine() {
        if(checkSelectedDrug() && checkSelectedAmount()) {
            displayAlertBox("Lijek je uspješno izabran!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedDrug() {
        if(drugsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            displayAlertBox("Nije izabran lijek!");
            return false;
        }
        return true;
    }

    private boolean checkSelectedAmount() {
        if(amountComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabrana količina lijeka!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
