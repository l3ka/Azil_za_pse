package GUI.admin.statistic_result.foster_parents;

import data.dto.FosterParentDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.time.LocalDate;
import java.util.List;

public class FosterParentsStatisticResultController {

    @FXML private Label titleLabel;
    @FXML private TableView<FosterParentDTO> fosterParentsTableView;

    private Stage stage;
    private LocalDate dateFrom;
    private List<FosterParentDTO> listOfFosterParents;

    public void initialize(Stage stage, LocalDate dateFrom) {
        this.stage = stage;
        this.dateFrom = dateFrom;

        titleLabel.setText("Udomitelji od " + dateFrom);

        fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
        fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
        fosterParentsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        displayFosterParents();
    }

    public void OKButtonPressed() {
        stage.close();
    }

    private void displayFosterParents() {
        listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents(dateFrom);
        fosterParentsTableView.getItems().clear();
        for(FosterParentDTO fosterParent : listOfFosterParents) {
            fosterParentsTableView.getItems().add(fosterParent);
        }
    }
}
