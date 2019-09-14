package GUI.preview.medicine_preview;

import GUI.adding_medicine.AddingMedicineForm;
import GUI.alert_box.AlertBoxForm;
import data.dto.DogDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class MedicinePreviewController {

    private Stage stage;
    private List<MedicineDTO> listOfMedicine;
    @FXML
    private TableView<MedicineDTO> medicineTableView;
    @FXML
    private Label differentTypesLabel;
    @FXML
    private Label medicineSummaryLabel;
    @FXML
    private TextField searchTextField;

    public void initialize(Stage stage) {
        this.stage = stage;
        medicineTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        medicineTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        medicineTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        displayMedicine();
    }

    private void displayMedicine() {
        medicineTableView.getItems().clear();
        medicineTableView.refresh();
        listOfMedicine = AzilUtilities.getDAOFactory().getMedicineDAO().medicines();
        differentTypesLabel.setText("Ukupno vrsta: " + listOfMedicine.size());
        int medicineSummary = 0;
        for (MedicineDTO medicine : listOfMedicine) {
            medicineTableView.getItems().add(medicine);
            medicineSummary += medicine.getQuantity();
        }
        medicineSummaryLabel.setText("Ukupno lijekova: " + medicineSummary);
    }

    public void addMedicine() {
        try {
            new AddingMedicineForm().display();
            displayMedicine();
        } catch (Exception ex) {

        }
    }

    public void editMedicine() {
        try {
            if (medicineTableView.getSelectionModel().getSelectedItem() == null) {
                new AlertBoxForm("Nije izabran lijek!").display();
            }
        } catch (Exception ex) {

        }
    }

    public void deleteMedicine() {
        try {
            if (medicineTableView.getSelectionModel().getSelectedItem() == null) {
                new AlertBoxForm("Nije izabran lijek!").display();
            }
        } catch (Exception ex) {

        }
    }

    public void search() {
        try {

        } catch (Exception ex) {

        }
    }

    public void showAll() {
        try {

        } catch (Exception ex) {

        }
    }

    public void close() {
        this.stage.close();
    }

    public void searchParameters() {
        String value = searchTextField.getText();
        List<MedicineDTO> filteredList = listOfMedicine.stream().filter((medicineDTO -> medicineDTO.getName().contains(value))).collect(Collectors.toList());
        medicineTableView.getItems().clear();
        medicineTableView.refresh();
        for (MedicineDTO medicine : filteredList) {
            medicineTableView.getItems().add(medicine);
        }
        medicineTableView.refresh();
    }
}