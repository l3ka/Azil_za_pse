package GUI.adding_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class AddingDogController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField raceTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private ComboBox<CageDTO> cagesComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button choosePhotoButton;

    private File photo;
    private Stage stage;
    private List<CageDTO> listOfCages = AzilUtilities.getDAOFactory().getCageDAO().cages();
    private DogDTO dogToAdd;

    public void initialize(Stage stage) {
        try {
            genderComboBox.getItems().addAll("M", "Ž");

            for(CageDTO cage : listOfCages) {
                if(cage.getCapacity() >= 1) {
                    cagesComboBox.getItems().add(cage);
                }
            }

            this.stage = stage;
            initButtonEvent();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
            saveButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    saveButton.fire();
                    e.consume();
                }
            });
            quitButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    quitButton.fire();
                    e.consume();
                }
            });
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void choosePhoto() {
        try {
            FileChooser fileChooser = new FileChooser();
            photo = fileChooser.showOpenDialog(stage);
            choosePhotoButton.setText("Promijeni fotografiju");
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - choosePhoto", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addDog() {
        try {
            String image;
            if (!checkPhoto()) {
                image = null;
            } else {
                image = photo.getAbsolutePath();
            }
            if (checkName() && checkRace() && checkGender() && checkWeight() && checkHeight() && checkAge() && checkSelectedCage()) {
                dogToAdd = new DogDTO(0, genderComboBox.getSelectionModel().getSelectedItem(), nameTextField.getText(),
                        raceTextField.getText(), Integer.parseInt(heightTextField.getText()), Double.parseDouble(weightTextField.getText()), Date.valueOf(dateOfBirthDatePicker.getValue()), image, false);
                if (AzilUtilities.getDAOFactory().getDogDAO().insert(dogToAdd)) {
                    displayAlertBox("Pas je uspješno dodat!");
                }
                AzilUtilities.getDAOFactory().getDogInCageDAO().insert(new DogInCageDTO(AzilUtilities.getDAOFactory().getDogDAO().getLastDog(), cagesComboBox.getSelectionModel().getSelectedItem(), new Timestamp(Calendar.getInstance().getTime().getTime()), null));
                cagesComboBox.getSelectionModel().getSelectedItem().setCapacity(cagesComboBox.getSelectionModel().getSelectedItem().getCapacity() - 1);
                AzilUtilities.getDAOFactory().getCageDAO().update(cagesComboBox.getSelectionModel().getSelectedItem());
                quit();
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - addDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        try {
            if("".equals(nameTextField.getText().trim())) {
                displayAlertBox("Unos za polje ime nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkRace() {
        try {
            if("".equals(raceTextField.getText().trim())) {
                displayAlertBox("Unos za polje rasa nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkRace", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkGender() {
        try {
            if(genderComboBox.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Pol psa nije izabran!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkGender", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkWeight() {
        try {
            if("".equals(weightTextField.getText().trim()) || !checkNumber(weightTextField.getText().trim())) {
                displayAlertBox("Unos za polje težina nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkWeight", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkHeight() {
        try {
            if("".equals(heightTextField.getText().trim()) || !checkNumber(heightTextField.getText().trim())) {
                displayAlertBox("Unos za polje visina nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkHeight", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkAge() {
        try {
            if(dateOfBirthDatePicker.getValue() == null) {
                displayAlertBox("Unos za polje rođenje nije odgovarajući!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkAge", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPhoto() {
        try {
            if(photo == null) {
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkPhoto", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkSelectedCage() {
        try {
            if(cagesComboBox.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran kavez!");
                return false;
            } else if(cagesComboBox.getSelectionModel().getSelectedItem().getCapacity() == 0) {
                displayAlertBox("Izabrani kavez je popunjen, izaberite drugi!");
                return false;
            }
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - checkSelectedCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AddingDogController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
