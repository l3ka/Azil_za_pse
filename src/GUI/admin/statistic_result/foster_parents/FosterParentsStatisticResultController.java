package GUI.admin.statistic_result.foster_parents;

import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class FosterParentsStatisticResultController {

    @FXML
    private Label titleLabel;
    @FXML
    private TableView<FosterParentDTO> fosterParentsTableView;
    @FXML
    private Button fosterParentsStatisticOk;
    @FXML
    private Button generatePdf;

    private Stage stage;
    private LocalDate dateFrom;
    private List<FosterParentDTO> listOfFosterParents;

    public void initialize(Stage stage, LocalDate dateFrom) {
        try {
            this.stage = stage;
            this.dateFrom = dateFrom;

            titleLabel.setText("Udomitelji od " + dateFrom);

            fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("JMB"));
            fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
            fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
            fosterParentsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            displayFosterParents();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsStatisticResultController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            fosterParentsStatisticOk.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    fosterParentsStatisticOk.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsStatisticResultController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void OKButtonPressed() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsStatisticResultController - OKButtonPressed", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void generatePDFButtonPressed() {
        try {
            AzilUtilities.getDAOFactory().getPdfExporterDAO().exportFosters(fosterParentsTableView, titleLabel.getText().trim().split(" ")[2]);
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsStatisticResultController - GeneratePDFButtonPressed", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayFosterParents() {
        try {
            fosterParentsTableView.getItems().clear();
            fosterParentsTableView.refresh();
            listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents(dateFrom);
            fosterParentsTableView.getItems().clear();
            for(FosterParentDTO fosterParent : listOfFosterParents) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("FosterParentsStatisticResultController - displayFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
