package GUI.vet.taking_medicine;

import GUI.alert_box.AlertBoxForm;
import GUI.vet.medicine_quantity.MedicineQuantityForm;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

public class TakingMedicineController {

    @FXML private TableView<MedicineDTO> drugsTableView;
    private Stage stage;
    private List<MedicineDTO> listOfMedicines;

    public void initialize(Stage stage) {
        this.stage = stage;

        drugsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        drugsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        drugsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));

        displayMedicines();
    }

    public void takeMedicine() {
        if(checkSelectedDrug()) {
            try {
                new MedicineQuantityForm(drugsTableView.getSelectionModel().getSelectedItem()).display();
                drugsTableView.getItems().clear();
                displayMedicines();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
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

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }

    private void displayMedicines() {
        listOfMedicines = AzilUtilities.getDAOFactory().getMedicineDAO().medicines();
        for(MedicineDTO medicine : listOfMedicines) {
            drugsTableView.getItems().add(medicine);
        }
    }
}
