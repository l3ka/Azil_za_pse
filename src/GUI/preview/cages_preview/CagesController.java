package GUI.preview.cages_preview;

import GUI.adding_cage.AddingCageForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_cage.EditingCageForm;
import data.dto.CageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

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
        this.stage = stage;

        cagesTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        cagesTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("capacity"));

        displayCages();
    }

    public void addCage() {
        try {
            new AddingCageForm().display();
            displayCages();
        } catch(Exception ex) {

        }
    }

    public void updateCage() {
        if(checkSelectedCage()) {
            try {
                new EditingCageForm(cagesTableView.getSelectionModel().getSelectedItem()).display();
                displayCages();
            } catch(Exception ex) {

            }
        }
    }

    public void deleteCage() {
        try {
            if (checkSelectedCage()) {
                boolean choice = new DecideBox("Da li ste sigurni da želite da obrišete kavez?").display();
                if (choice) {
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

        }
    }

    public void search() {
        try {
            String inputValue = nameTextField.getText().toUpperCase();
            List<CageDTO> filteredList = listOfCages.stream().filter(cageDTO -> cageDTO.getName().toUpperCase().contains(inputValue)).collect(Collectors.toList());
            cagesTableView.getItems().clear();
            for (CageDTO cage : filteredList) {
                cagesTableView.getItems().add(cage);
            }
            cagesTableView.refresh();
        }  catch (Exception ex) {

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
        } catch (Exception ex) {

        }
    }

    public void quit() {
        stage.close();
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }

    private void displayCages() {
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
    }


    private boolean checkSelectedCage() {
        if(cagesTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran kavez!");
            return false;
        }
        return true;
    }
}
