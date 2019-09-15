package GUI.adopting_dog.adopting_main;

import GUI.adopting_dog.adopt_dog.AdoptingDogForm;
import GUI.alert_box.AlertBoxForm;
import data.dao.AdoptingDAO;
import data.dto.AdoptingDogDTO;
import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptingMainController {

    private Stage stage;
    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label placeOfResidenceLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private ImageView dogImageView;

    private List<DogDTO> listOfDogs;
    private List<AdoptingDogDTO> listOfAdoptings;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
            dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
            dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
            dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            allAdoptings();
            displayDogs();
            dogsTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                try {
                    if (newValue == null) return;
                    if (newValue.getImage() != null) {
                        dogImageView.setImage(new Image("file:" + newValue.getImage()));
                    } else dogImageView.setImage(null);
                    String fosterParentJMB = listOfAdoptings.stream().
                            filter(adoptingDogDTO -> adoptingDogDTO.getIdDog() == newValue.getDogId() && adoptingDogDTO.getDateTo() == null).
                            findAny().get().getJmbFosterParent();
                    FosterParentDTO fosterParent = AzilUtilities.getDAOFactory().getFosterParentDAO().getByJMB(fosterParentJMB);
                    nameLabel.setText(fosterParent.getName());
                    surnameLabel.setText(fosterParent.getSurname());
                    placeOfResidenceLabel.setText(fosterParent.getResidenceAddress());
                    phoneLabel.setText(fosterParent.getTelephoneNumber());
                } catch (Exception ex) {
                    AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
                }
            }));
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingMainController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    public void adoptDog() {
        try {
            new AdoptingDogForm().display();
            displayDogs();
            allAdoptings();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingMainController - adoptDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingMainController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    private void displayDogs() {
        try {
            dogsTableView.getItems().clear();
            dogsTableView.refresh();
            listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

            for(DogDTO dog : listOfDogs) {
                if(dog.isAdopted()) {
                    dogsTableView.getItems().add(dog);
                }
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingMainController - displayDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void allAdoptings() {
        try {
            listOfAdoptings = AzilUtilities.getDAOFactory().getAdoptingDogDAO().getAllAdoptings();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingMainController - allAdoptings", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
