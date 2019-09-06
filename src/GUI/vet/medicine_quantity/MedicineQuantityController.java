package GUI.vet.medicine_quantity;

import GUI.alert_box.AlertBoxForm;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AzilUtilities;

public class MedicineQuantityController {

    @FXML
    private Label medicineNameLabel;
    @FXML
    private ComboBox<Integer> quantityComboBox;

    private Stage stage;
    private MedicineDTO medicine = MedicineQuantityForm.medicine;

    public void initialize(Stage stage) {
        this.stage = stage;

        medicineNameLabel.setText(medicine.getName());
        for(int i = 1; i <= medicine.getQuantity(); i++) {
            quantityComboBox.getItems().add(i);
        }
    }

    public void save() {
        if(checkSelectedQuantity()) {
            medicine.setQuantity(medicine.getQuantity() - quantityComboBox.getSelectionModel().getSelectedItem());
            AzilUtilities.getDAOFactory().getMedicineDAO().update(medicine);
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedQuantity() {
        if(quantityComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabrana količina!");
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
}
