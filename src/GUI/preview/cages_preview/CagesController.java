package GUI.preview.cages_preview;

import GUI.adding_cage.AddingCageForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_cage.EditingCageForm;
import data.dto.CageDTO;
import data.dto.LoggerDTO;
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

public class CagesController {

    @FXML
    private TableView<CageDTO> cagesTableView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label numberOfCagesLabel;
    @FXML
    private Label numberOfFreeSpotsLabel;

    private Stage stage;
    private List<CageDTO> listOfCages;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            cagesTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            cagesTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("capacity"));
            cagesTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fullCapacity"));

            displayCages();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addCage() {
        try {
            new AddingCageForm().display();
            displayCages();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - addCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void updateCage() {
        try {
            if(checkSelectedCage()) {
                new EditingCageForm(cagesTableView.getSelectionModel().getSelectedItem()).display();
                displayCages();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - updateCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deleteCage() {
        try {
            if (checkSelectedCage()) {
                if (new DecideBox("Da li ste sigurni da želite da obrišete kavez?").display()) {
                    if (AzilUtilities.getDAOFactory().getCageDAO().delete(cagesTableView.getSelectionModel().getSelectedItem().getId())) {
                        displayAlertBox("Kavez je uspješno obrisan!");
                    }
                    else {
                        displayAlertBox("Desila se greška prilikom brisanja kaveza!");
                    }
                    displayCages();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - deleteCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            List<CageDTO> filteredList = listOfCages.stream()
                                                    .filter(cageDTO -> cageDTO.getName().toUpperCase().contains(nameTextField.getText().toUpperCase()))
                                                    .collect(Collectors.toList());
            cagesTableView.getItems().clear();
            for (CageDTO cage : filteredList) {
                cagesTableView.getItems().add(cage);
            }
            cagesTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllCages() {
        try {
            nameTextField.clear();
            cagesTableView.getItems().clear();
            for (CageDTO cage : listOfCages) {
                cagesTableView.getItems().add(cage);
            }
            cagesTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayAllCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayCages() {
        try {
            int numberOfFreeSpots = 0;
            cagesTableView.getItems().clear();
            cagesTableView.refresh();
            listOfCages = AzilUtilities.getDAOFactory().getCageDAO().cages();
            numberOfCagesLabel.setText("Broj kaveza: " + listOfCages.size());
            for(CageDTO cage : listOfCages) {
                cagesTableView.getItems().add(cage);
                numberOfFreeSpots += cage.getCapacity();
            }
            numberOfFreeSpotsLabel.setText("Broj slobodnih mjesta: " + numberOfFreeSpots);
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    private boolean checkSelectedCage() {
        try {
            if(cagesTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran kavez!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - checkSelectedCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

}
