package GUI.editing_medicine;

import GUI.alert_box.AlertBoxForm;
import data.dto.LoggerDTO;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class EditingMedicineController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;

    private Stage stage;
    private MedicineDTO medicine;

    public void initialize(Stage stage, MedicineDTO medicine) {
        try {
            this.stage = stage;
            this.medicine = medicine;

            nameTextField.setText(medicine.getName());
            amountTextField.setText("" + medicine.getQuantity());
            descriptionTextField.setText(medicine.getDescription());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void save() {
        try {
            if(checkName() && checkAmount()) {
                medicine.setName(nameTextField.getText());
                medicine.setQuantity(Integer.valueOf(amountTextField.getText()));
                medicine.setDescription(descriptionTextField.getText());
                if(AzilUtilities.getDAOFactory().getMedicineDAO().update(medicine)) {
                    displayAlertBox("Podaci o lijeku su uspješno izmijenjeni!");
                    stage.close();
                }
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - save", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        try {
            if("".equals(nameTextField.getText().trim())) {
                displayAlertBox("Unos za polje naziv nije odgovarajući!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkAmount() {
        try {
            if("".equals(amountTextField.getText().trim()) || !checkNumber(amountTextField.getText().trim())) {
                displayAlertBox("Unos za polje količina nije odgovarajući!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - checkAmount", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - checkNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingMedicineController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
