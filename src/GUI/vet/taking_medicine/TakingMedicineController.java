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
            try {
                new AlertBoxForm("Lijek je uspješno izabran!").display();
            } catch(Exception exception) {

            }
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedDrug() {
        if(drugsTableView.getSelectionModel().getSelectedCells().isEmpty()) {
            try {
                new AlertBoxForm("Nije izabran lijek!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkSelectedAmount() {
        if(amountComboBox.getSelectionModel().getSelectedItem() == null) {
            try {
                new AlertBoxForm("Nije izabrana količina lijeka!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }
}
