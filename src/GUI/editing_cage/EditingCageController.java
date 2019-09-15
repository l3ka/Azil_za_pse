package GUI.editing_cage;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

public class EditingCageController {

    @FXML
    private TextField cageNameTextField;
    @FXML
    private TextField cageCapacityTextField;

    private Stage stage;
    private CageDTO cage;

    public void initialize(Stage stage, CageDTO cage) {
        try {
            this.stage = stage;
            this.cage = cage;

            cageNameTextField.setText(cage.getName());
            cageCapacityTextField.setText("" + cage.getCapacity());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void save() {
        try {
            if(checkName() && checkCapacity()) {
                cage.setName(cageNameTextField.getText());
                cage.setCapacity(Integer.valueOf(cageCapacityTextField.getText()));

                if(AzilUtilities.getDAOFactory().getCageDAO().update(cage)) {
                    displayAlertBox("Kavez je uspješno izmijenjen!");
                    stage.close();
                }
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - save", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        stage.close();
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        try {
            if("".equals(cageNameTextField.getText())) {
                displayAlertBox("Nije uneseno ime kaveza!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkCapacity() {
        try {
            if("".equals(cageCapacityTextField.getText())) {
                displayAlertBox("Nije unesen kapacitet kaveza!");
                return false;
            }
            if(!checkNumber(cageCapacityTextField.getText())) {
                displayAlertBox("Unos za polje kapacitet nije odgovarajući!");
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - checkCapacity", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkNumber(String content) {
        try {
            Integer.parseInt(content);
            return true;
        } catch (NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingCageController - checkNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

}
