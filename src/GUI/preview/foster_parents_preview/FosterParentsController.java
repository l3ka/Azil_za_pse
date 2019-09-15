package GUI.preview.foster_parents_preview;

import GUI.add_foster_parent.AddFosterParent;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.edit_foster_parent.EditFosterParentForm;
import data.dto.FosterParentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class FosterParentsController {
    @FXML
    private TableView<FosterParentDTO> fosterParentsTableView;
    @FXML
    private TextField nameTextField;

    private Stage stage;
    private List<FosterParentDTO> listOfFosterParents;


    public void initialize(Stage stage) {
        this.stage = stage;

        fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
        fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        fosterParentsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        displayFosterParents();
    }

    public void addFosterParent() {
        try {
            new AddFosterParent().display();
        } catch(Exception ex) {

        }
    }

    public void updateFosterParent() {
        if(checkSelectedFosterParent()) {
            try {
                new EditFosterParentForm(fosterParentsTableView.getSelectionModel().getSelectedItem()).display();
            } catch(Exception ex) {

            }
        }
    }

    public void deleteFosterParent() {
        try {
            if (checkSelectedFosterParent()) {
                boolean choice = new DecideBox("Da li ste sigurni da želite da obrišete udomitelja?").display();
                if (choice) {
                   if  (AzilUtilities.getDAOFactory().getFosterParentDAO().delete(fosterParentsTableView.getSelectionModel()
                            .getSelectedItem().getJMB())) {
                       displayAlertBox("Udomitelj je uspješno obrisan!");
                   }
                   else {
                       displayAlertBox("Desila se greška prilikom brisanja udomitelja!");
                   }
                   displayAllFosterParents();
                }

            }
        } catch (Exception ex) {

        }
    }

    public void displayAllFosterParents() {
        if (nameTextField.getText() == null) return;
        fosterParentsTableView.getItems().clear();
        for (FosterParentDTO fosterParent : listOfFosterParents) {
            fosterParentsTableView.getItems().add(fosterParent);
        }
        fosterParentsTableView.refresh();
        nameTextField.clear();
    }

    public void search() {
        try {
            String inputText = nameTextField.getText().toUpperCase();
            List<FosterParentDTO> filteredList = listOfFosterParents.stream().
                    filter(fosterParentDTO -> fosterParentDTO.getName().toUpperCase().contains(inputText)).
                    collect(Collectors.toList());
            fosterParentsTableView.getItems().clear();
            for (FosterParentDTO fosterParent : filteredList) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            fosterParentsTableView.refresh();
        } catch (Exception ex) {

        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkSelectedFosterParent() {
        if(fosterParentsTableView.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran udomitelj!");
            return false;
        }
        return true;
    }

    private void displayFosterParents() {
        fosterParentsTableView.getItems().clear();
        fosterParentsTableView.refresh();
        listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents();
        for(FosterParentDTO fosterParent : listOfFosterParents) {
            fosterParentsTableView.getItems().add(fosterParent);
        }
    }


    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {

        }
    }
}
