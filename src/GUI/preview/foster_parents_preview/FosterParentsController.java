package GUI.preview.foster_parents_preview;

import GUI.add_foster_parent.AddFosterParent;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.edit_foster_parent.EditFosterParentForm;
import data.dto.AdoptingDogDTO;
import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class FosterParentsController {

    @FXML
    private TableView<FosterParentDTO> fosterParentsTableView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private HBox alert;

    private Stage stage;
    private List<FosterParentDTO> listOfFosterParents;
    private List<AdoptingDogDTO> listOfAdoptings;


    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
            fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
            fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
            fosterParentsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            displayFosterParents();
            allAdoptings();
            fosterParentsTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                try {
                    if (newValue == null) return;
                    for (AdoptingDogDTO adoptingDog : listOfAdoptings) {
                        if (adoptingDog.getJmbFosterParent().equals(newValue.getJMB())) {
                            deleteButton.setDisable(true);
                            alert.setVisible(true);
                            return;
                        }
                    }
                    deleteButton.setDisable(false);
                    alert.setVisible(false);
                } catch (Exception ex) {
                    AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
                }
            }));
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addFosterParent() {
        try {
            new AddFosterParent().display();
            displayFosterParents();
            allAdoptings();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - addFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void updateFosterParent() {
        try {
            if(checkSelectedFosterParent()) {
                new EditFosterParentForm(fosterParentsTableView.getSelectionModel().getSelectedItem()).display();
                displayFosterParents();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - updateFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deleteFosterParent() {
        try {
            if (checkSelectedFosterParent()) {
                if (new DecideBox("Da li ste sigurni da želite da obrišete udomitelja?").display()) {
                   if  (AzilUtilities.getDAOFactory().getFosterParentDAO().delete(fosterParentsTableView.getSelectionModel().getSelectedItem().getJMB())) {
                       displayAlertBox("Udomitelj je uspješno obrisan!");
                   }
                   else {
                       displayAlertBox("Desila se greška prilikom brisanja udomitelja!");
                   }
                   displayFosterParents();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - deleteFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllFosterParents() {
        try {
            if (nameTextField.getText() == null) {
                return;
            }
            fosterParentsTableView.getItems().clear();
            for (FosterParentDTO fosterParent : listOfFosterParents) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            fosterParentsTableView.refresh();
            nameTextField.clear();
            deleteButton.setDisable(false);
            alert.setVisible(false);
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - displayAllFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            deleteButton.setDisable(false);
            alert.setVisible(false);
            String inputText = nameTextField.getText().toUpperCase();
            List<FosterParentDTO> filteredList = listOfFosterParents.stream()
                                                                     .filter(fosterParentDTO -> fosterParentDTO.getName().toUpperCase().contains(inputText))
                                                                     .collect(Collectors.toList());
            fosterParentsTableView.getItems().clear();
            for (FosterParentDTO fosterParent : filteredList) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            fosterParentsTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedFosterParent() {
        try {
            if(fosterParentsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran udomitelj!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - checkSelectedFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayFosterParents() {
        try {
            fosterParentsTableView.getItems().clear();
            fosterParentsTableView.refresh();
            listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents();
            for(FosterParentDTO fosterParent : listOfFosterParents) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            deleteButton.setDisable(false);
            alert.setVisible(false);
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - displayFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void allAdoptings() {
        try {
            listOfAdoptings = AzilUtilities.getDAOFactory().getAdoptingDogDAO().getAllAdoptings();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsController - allAdoptings", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
