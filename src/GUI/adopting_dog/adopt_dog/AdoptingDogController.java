package GUI.adopting_dog.adopt_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptingDogController {

    @FXML
    private TableView<DogDTO> dogsTableView;
    @FXML
    private TableView<FosterParentDTO> fosterParentsTableView;
    @FXML
    private Button adoptDogButton;
    @FXML
    private Button quitButton;
    @FXML
    private TextField searchDogsTextField;
    @FXML
    private TextField searchFosterParentsTextField;

    private List<DogDTO> listOfDogs;
    private List<FosterParentDTO> listOfFosterParents;
    private DogDTO selectedDog;
    private FosterParentDTO selectedFosterParent;
    private AdoptingDTO adoptingDTO;
    private Stage stage;
    private CageDTO cage;

    @FXML
    public void initialize(Stage stage) {
        try {
            this.stage = stage;

            dogsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            dogsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("breed"));
            dogsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            dogsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("height"));
            dogsTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
            dogsTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

            fosterParentsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            fosterParentsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("surname"));
            fosterParentsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("residenceAddress"));
            fosterParentsTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

            displayDogs();
            displayFosterParents();
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            adoptDogButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    adoptDogButton.fire();
                    e.consume();
                }
            });
            quitButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    quitButton.fire();
                    e.consume();
                }
            });
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void adoptDog() {
        try {
            if(checkDog() && checkFosterParent()) {
                selectedDog = dogsTableView.getSelectionModel().getSelectedItem();
                selectedFosterParent = fosterParentsTableView.getSelectionModel().getSelectedItem();

                selectedDog.setAdopted(true);
                cage = AzilUtilities.getDAOFactory().getDogInCageDAO().getCage(selectedDog);
                cage.setCapacity(cage.getCapacity() + 1);
                AzilUtilities.getDAOFactory().getCageDAO().update(cage);

                AzilUtilities.getDAOFactory().getDogDAO().update(selectedDog);
                adoptingDTO = new AdoptingDTO(selectedDog, new Date(Calendar.getInstance().getTime().getTime()), null);
                selectedFosterParent.addDog(adoptingDTO);

                AzilUtilities.getDAOFactory().getAdoptingDAO().insert(selectedFosterParent, adoptingDTO);
                displayAlertBox("Pas je uspješno udomljen!");
                quit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - adoptDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void searchDogs() {
        try {
            List<DogDTO> filteredList = listOfDogs.stream()
                                                  .filter((dog -> dog.getBreed().toUpperCase().contains(searchDogsTextField.getText().toUpperCase())))
                                                  .collect(Collectors.toList());
            dogsTableView.getItems().clear();
            for (DogDTO dog : filteredList) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - searchDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }

    }

    public void searchFosterParents() {
        try {
            List<FosterParentDTO> filteredList = listOfFosterParents.stream()
                                                                    .filter(fosterParentDTO -> fosterParentDTO.getName().toUpperCase().contains(searchFosterParentsTextField.getText().toUpperCase()))
                                                                    .collect(Collectors.toList());
            fosterParentsTableView.getItems().clear();
            for (FosterParentDTO fosterParent : filteredList) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            fosterParentsTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - searchFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void showAllDogs() {
        try {
            if (searchDogsTextField.getText() == null) return;
            dogsTableView.getItems().clear();
            for (DogDTO dog : listOfDogs) {
                dogsTableView.getItems().add(dog);
            }
            dogsTableView.refresh();
            searchDogsTextField.clear();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - showAllDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void showAllFosterParents() {
        try {
            if (searchFosterParentsTextField.getText() == null) return;
            fosterParentsTableView.getItems().clear();
            for (FosterParentDTO fosterParent : listOfFosterParents) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
            fosterParentsTableView.refresh();
            searchFosterParentsTextField.clear();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - showAllFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }

    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkDog() {
        try {
            if(dogsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran pas!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - checkDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkFosterParent() {
        try {
            if(fosterParentsTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran udomitelj!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - checkFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }


    private void displayDogs() {
        try {
            listOfDogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();
            for (DogDTO dog : listOfDogs) {
                if(!dog.isAdopted()) {
                    dogsTableView.getItems().add(dog);
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - displayDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayFosterParents() {
        try {
            listOfFosterParents = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents();
            for(FosterParentDTO fosterParent : listOfFosterParents) {
                fosterParentsTableView.getItems().add(fosterParent);
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - displayFosterParents", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AdoptingDogController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
