package GUI.preview.foster_parents_preview;

import GUI.add_foster_parent.AddFosterParent;
import GUI.alert_box.AlertBoxForm;
import GUI.edit_foster_parent.EditFosterParentForm;
import data.dto.FosterParentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.util.List;

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
        if(checkSelectedFosterParent()) {
            AzilUtilities.getDAOFactory().getFosterParentDAO().delete(fosterParentsTableView.getSelectionModel().getSelectedItem().getJMB());
        }
    }

    public void displayAllFosterParents() {
        displayFosterParents();
    }

    public void search() {
        if(checkName()) {

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
        listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents();
        for(FosterParentDTO fosterParent : listOfFosterParents) {
            fosterParentsTableView.getItems().add(fosterParent);
        }
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            displayAlertBox("Niste unijeli ime za pretragu!");
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
