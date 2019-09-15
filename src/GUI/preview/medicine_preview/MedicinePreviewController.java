package GUI.preview.medicine_preview;

import GUI.adding_medicine.AddingMedicineForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_medicine.EditingMedicineForm;
import data.dto.LoggerDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
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
        try {
            this.stage = stage;
            medicineTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            medicineTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            medicineTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantity"));
            displayMedicines();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayMedicines() {
        try {
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - displayMedicines", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addMedicine() {
        try {
            new AddingMedicineForm().display();
            displayMedicines();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - addMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void editMedicine() {
        try {
            if(checkSelectedMedicine()) {
                new EditingMedicineForm(medicineTableView.getSelectionModel().getSelectedItem()).display();
                displayMedicines();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - editMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deleteMedicine() {
        try {
            if (checkSelectedMedicine()) {
               boolean choice = new DecideBox("Da li ste sigurno da želite da obrišete lijek?").display();
                if (choice) {
                    if (AzilUtilities.getDAOFactory().getMedicineDAO().delete(medicineTableView.getSelectionModel().getSelectedItem().getMedicineId())) {
                        displayAlertBox("Lijek je uspješno obrisan!");
                    }
                    else {
                        displayAlertBox("Desila se greška prilikom brisanja lijeka!");
                    }
                    displayMedicines();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - deleteMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void showAll() {
        try {
            medicineTableView.getItems().clear();
            for (MedicineDTO medicine : listOfMedicine) {
                medicineTableView.getItems().add(medicine);
            }
            medicineTableView.refresh();
            searchTextField.clear();

        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - showAll", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedMedicine() {
        try {
            if (medicineTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran lijek!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - checkSelectedMedicine", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void close() {
        try {
            this.stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - close", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            String value = searchTextField.getText().toUpperCase();
            List<MedicineDTO> filteredList = listOfMedicine.stream().filter((medicineDTO -> medicineDTO.getName().toUpperCase().contains(value))).collect(Collectors.toList());
            medicineTableView.getItems().clear();
            medicineTableView.refresh();
            for (MedicineDTO medicine : filteredList) {
                medicineTableView.getItems().add(medicine);
            }
            medicineTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MedicinePreviewController - searchParameters", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}