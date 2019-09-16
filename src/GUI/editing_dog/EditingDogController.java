package GUI.editing_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class EditingDogController {

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

    private Stage stage;
    private DogDTO dog;
    private File photo;
    private CageDTO cage;

    public void initialize(Stage stage, DogDTO dog) {
        try {
            this.stage = stage;
            this.dog = dog;

            genderComboBox.getItems().addAll("M", "Ž");
            for(CageDTO cage : AzilUtilities.getDAOFactory().getCageDAO().cages()) {
                cagesComboBox.getItems().add(cage);
            }

            genderComboBox.getSelectionModel().select(dog.getGender());
            cagesComboBox.getSelectionModel().select(AzilUtilities.getDAOFactory().getDogInCageDAO().getCage(dog));
            nameTextField.setText(dog.getName());
            raceTextField.setText(dog.getBreed());
            weightTextField.setText("" + dog.getWeight());
            heightTextField.setText("" + dog.getHeight());
            dateOfBirthDatePicker.setValue(dog.getDateOfBirth().toLocalDate());
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void choosePhoto() {
        try {
            FileChooser fileChooser = new FileChooser();
            photo = fileChooser.showOpenDialog(stage);
            choosePhotoButton.setText("Promijeni fotografiju");
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - choosePhoto", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void save() {
        try {
            String image;
            if (!checkPhoto()) {
                image = null;
            }
            else {
                image = photo.getAbsolutePath();
            }
            if (checkName() && checkRace() && checkGender() && checkWeight() && checkHeight() && checkAge() && checkSelectedCage()) {
                cage = AzilUtilities.getDAOFactory().getDogInCageDAO().getCage(dog);
                if(!cagesComboBox.getSelectionModel().getSelectedItem().equals(cage)) {
                    cage.setCapacity(cage.getCapacity() + 1);
                    AzilUtilities.getDAOFactory().getDogInCageDAO().getDogInCage(cage.getId(), dog.getDogId()).setDateTo(new Timestamp(Calendar.getInstance().getTime().getTime()));
                    cagesComboBox.getSelectionModel().getSelectedItem().setCapacity(cagesComboBox.getSelectionModel().getSelectedItem().getCapacity() - 1);
                    AzilUtilities.getDAOFactory().getDogInCageDAO().insert(new DogInCageDTO(dog, cagesComboBox.getSelectionModel().getSelectedItem(), new Timestamp(Calendar.getInstance().getTime().getTime()), null));
                }
                dog.setName(nameTextField.getText());
                dog.setGender(genderComboBox.getSelectionModel().getSelectedItem());
                dog.setBreed(raceTextField.getText());
                dog.setHeight(Integer.valueOf(heightTextField.getText()));
                dog.setWeight(Double.valueOf(weightTextField.getText()));
                dog.setDateOfBirth(Date.valueOf(dateOfBirthDatePicker.getValue()));
                dog.setImage(image);

                if (AzilUtilities.getDAOFactory().getDogDAO().update(dog)) {
                    displayAlertBox("Informacije o psu su uspješno izmijenjene!");
                    stage.close();
                }
            }
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - save", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private boolean checkName() {
        try {
            if("".equals(nameTextField.getText().trim())) {
                displayAlertBox("Unos za polje ime nije odgovarajući!");
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkRace", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkGender", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkWeight", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkHeight", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkAge", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkPhoto() {
        try {
            if(photo == null) {
                return false;
            }
            return true;
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkPhoto", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkNumber", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - checkSelectedCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("EditingDogController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

}
