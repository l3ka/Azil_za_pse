package GUI.adding_dog;

import GUI.alert_box.AlertBoxForm;
import data.dto.DogDTO;
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

public class AddingDogController {

    @FXML private TextField nameTextField;
    @FXML private TextField raceTextField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextField weightTextField;
    @FXML private TextField heightTextField;
    @FXML private DatePicker dateofBirthDatePicker;
    private File photo;
    private Stage stage;

    public void initialize(Stage stage) {
        genderComboBox.getItems().addAll("M", "Ž");
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
        if (checkName() && checkRace() && checkGender() && checkWeight() && checkHeight() && checkAge()) {
            if (AzilUtilities.getDAOFactory().getDogDAO().insert(new DogDTO(0, genderComboBox.getSelectionModel().getSelectedItem(), nameTextField.getText(),
                    raceTextField.getText(), Integer.parseInt(heightTextField.getText()), Double.parseDouble(weightTextField.getText()), Date.valueOf(dateofBirthDatePicker.getValue()), image))) {
                displayAlertBox("Pas je uspješno dodat!");
                stage.close();
            }
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

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception exception) {

        }
    }
}
