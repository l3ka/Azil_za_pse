package GUI.preview.dogs_preview;

import GUI.adding_dog.AddingDogForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_dog.EditingDogForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class DogsPreviewController {

    @FXML
    private ImageView dogImageView;
    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private TextField searchDogsTextField;

    private Stage stage;
    private List<DogDTO> listOfDogs;
    private CageDTO cage;
    private DogInCageDTO dogInCage;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
            dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
            dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
            dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            dogsTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                try {
                    if (newValue == null) return;
                    if (newValue.getImage() != null) {
                        dogImageView.setImage(new Image("file:" + newValue.getImage()));
                    } else {
                        dogImageView.setImage(null);
                    }
                } catch (Exception ex) {
                    AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
                }
            }));


            displayDogs();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addDog() {
        try {
            new AddingDogForm().display();
            displayDogs();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - addDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void editDog() {
        try {
            if(checkSelectedDog()) {
                new EditingDogForm(dogsTableView.getSelectionModel().getSelectedItem()).display();
                displayDogs();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - editDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deleteDog() {
        try {
            if (checkSelectedDog()) {
                boolean choice = new DecideBox("Da li ste sigurni da želite da obrišete psa?").display();
                if (choice) {
                    cage = AzilUtilities.getDAOFactory().getDogInCageDAO().getCage(dogsTableView.getSelectionModel().getSelectedItem());
                    dogInCage = AzilUtilities.getDAOFactory().getDogInCageDAO().getDogInCage(cage.getId(), dogsTableView.getSelectionModel().getSelectedItem().getDogId());
                    if(AzilUtilities.getDAOFactory().getDogInCageDAO().delete(Timestamp.valueOf(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(dogInCage.getDateForm())), dogInCage.getCage().getId(), dogInCage.getDog().getDogId())) {
                        cage.setCapacity(cage.getCapacity() + 1);
                    }
                    AzilUtilities.getDAOFactory().getCageDAO().update(cage);
                    if (AzilUtilities.getDAOFactory().getDogDAO().delete(dogsTableView.getSelectionModel().getSelectedItem())) {
                       displayAlertBox("Pas je uspješno obrisan!");
                       displayDogs();
                    }
                    else {
                        displayAlertBox("Desila se greška prilikom brisanja!");
                    }
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - deleteDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            List<DogDTO> filteredList = listOfDogs.stream()
                                                  .filter((dog -> dog.getBreed().toUpperCase().contains(searchDogsTextField.getText().toUpperCase())))
                                                  .collect(Collectors.toList());
            dogsTableView.getItems().clear();
            for (DogDTO dog : filteredList) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void showAll() {
        try {
            if (searchDogsTextField.getText() == null) {
                return;
            }
            dogsTableView.getItems().clear();
            for (DogDTO dog : listOfDogs) {
                if(!dog.isAdopted()) {
                    dogsTableView.getItems().add(dog);
                }
            }
            dogsTableView.refresh();
            searchDogsTextField.clear();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - showAll", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void close() {
        try {
            this.stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - close", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayDogs() {
        try {
            dogsTableView.getItems().clear();
            dogsTableView.refresh();
            listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
            for(DogDTO dog : listOfDogs) {
                if(!dog.isAdopted()) {
                    dogsTableView.getItems().add(dog);
                }
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - displayDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkSelectedDog() {
        try {
            if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran pas!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - checkSelectedDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("DogsPreviewController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
