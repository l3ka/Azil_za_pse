package GUI.adding_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    private File photo;
    private Stage stage;
    private List<CageDTO> listOfCages = AzilUtilities.getDAOFactory().getCageDAO().cages();
    private DogDTO dogToAdd;

    public void initialize(Stage stage) {
        genderComboBox.getItems().addAll("M", "Ž");

        for(CageDTO cage : listOfCages) {
            if(cage.getCapacity() >= 1) {
                cagesComboBox.getItems().add(cage);
            }
        }

        this.stage = stage;
        initButtonEvent();
    }

    private void initButtonEvent() {
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
    }

    public void choosePhoto() {
        FileChooser fileChooser = new FileChooser();
        photo = fileChooser.showOpenDialog(stage);
    }

    public void addDog() {
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
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText().trim())) {
            displayAlertBox("Unos za polje ime nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkRace() {
        if("".equals(raceTextField.getText().trim())) {
            displayAlertBox("Unos za polje rasa nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkGender() {
        if(genderComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Pol psa nije izabran!");
            return false;
        }
        return true;
    }

    private boolean checkWeight() {
        if("".equals(weightTextField.getText().trim()) || !checkNumber(weightTextField.getText().trim())) {
            displayAlertBox("Unos za polje težina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkHeight() {
        if("".equals(heightTextField.getText().trim()) || !checkNumber(heightTextField.getText().trim())) {
            displayAlertBox("Unos za polje visina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkAge() {
        if(dateOfBirthDatePicker.getValue() == null) {
            displayAlertBox("Unos za polje rođenje nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkPhoto() {
        if(photo == null) {
            return false;
        }
        return true;
    }

    private boolean checkNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException exception) {
            return false;
        }
    }

    private boolean checkSelectedCage() {
        if(cagesComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran kavez!");
            return false;
        } else if(cagesComboBox.getSelectionModel().getSelectedItem().getCapacity() == 0) {
            displayAlertBox("Izabrani kavez je popunjen, izaberite drugi!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception exception) {

        }
    }

}
