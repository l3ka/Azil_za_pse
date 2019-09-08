package GUI.adding_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class AddingDogController {

    @FXML private TextField nameTextField;
    @FXML private TextField raceTextField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextField weightTextField;
    @FXML private TextField heightTextField;
    @FXML private DatePicker dateofBirthDatePicker;
    @FXML private ComboBox<CageDTO> cagesComboBox;
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
                    raceTextField.getText(), Integer.parseInt(heightTextField.getText()), Double.parseDouble(weightTextField.getText()), Date.valueOf(dateofBirthDatePicker.getValue()), image, false);
            if (AzilUtilities.getDAOFactory().getDogDAO().insert(dogToAdd)) {
                displayAlertBox("Pas je uspješno dodat!");
            }
            AzilUtilities.getDAOFactory().getDogInCageDAO().insert(new DogInCageDTO(AzilUtilities.getDAOFactory().getDogDAO().getLastDog(), cagesComboBox.getSelectionModel().getSelectedItem(), new Date(Calendar.getInstance().getTime().getTime()), null));
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            displayAlertBox("Unos za polje ime nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkRace() {
        if("".equals(raceTextField.getText())) {
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
        if("".equals(weightTextField.getText()) || !checkNumber(weightTextField.getText())) {
            displayAlertBox("Unos za polje težina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkHeight() {
        if("".equals(heightTextField.getText()) || !checkNumber(heightTextField.getText())) {
            displayAlertBox("Unos za polje visina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkAge() {
        if(dateofBirthDatePicker.getValue() == null) {
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
