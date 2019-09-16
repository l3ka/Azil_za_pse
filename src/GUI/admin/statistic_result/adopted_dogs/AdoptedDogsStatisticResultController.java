package GUI.admin.statistic_result.adopted_dogs;

import data.dto.DogDTO;
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

public class AdoptedDogsStatisticResultController {

    @FXML
    private Label titleLabel;
    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private Button adopterDogStatisticOk;
    @FXML
    private Button generatePdf;

    private Stage stage;
    private LocalDate dateFrom;
    private List<DogDTO> listOfDogs;

    public void initialize(Stage stage, LocalDate dateFrom) {
        try {
            this.stage = stage;
            this.dateFrom = dateFrom;
            titleLabel.setText("Udomljeni psi od " + dateFrom);
            dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
            dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
            dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
            dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            displayDogs();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptedDogsStatisticResultController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            adopterDogStatisticOk.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    adopterDogStatisticOk.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptedDogsStatisticResultController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void OKButtonPressed() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptedDogsStatisticResultController - OKButtonPressed", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void generatePDFButtonPressed() {
        try {
            AzilUtilities.getDAOFactory().getPdfExporterDAO().exportAdoptedDogs(dogsTableView, titleLabel.getText().trim().split(" ")[3]);
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptedDogsStatisticResultController - GeneratePDFButtonPressed", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayDogs() {
        try {
            listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().getAdoptedDogs(dateFrom);
            dogsTableView.getItems().clear();
            for(DogDTO dog : listOfDogs) {
                dogsTableView.getItems().add(dog);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptedDogsStatisticResultController - displayDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
