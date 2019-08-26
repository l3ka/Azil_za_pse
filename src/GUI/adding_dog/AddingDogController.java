package GUI.adding_dog;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class AddingDogController {

    @FXML private TextField nameTextField;
    @FXML private TextField raceTextField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextField weightTextField;
    @FXML private TextField heightTextField;
    @FXML private TextField ageTextField;
    @FXML private Button choosePhotoButton;
    private File photo;

    @FXML
    private void initialize() {
        genderComboBox.getItems().addAll("Muški", "Ženski");
    }

    public void choosePhoto() {
        FileChooser fileChooser = new FileChooser();
        photo = fileChooser.showOpenDialog(choosePhotoButton.getScene().getWindow());
    }

    public void addDog() {
        if(checkName() && checkRace() && checkGender() && checkWeight() && checkHeight() && checkAge() && checkPhoto()) {
            try {
                new AlertBoxForm("Pas je uspješno dodat!").display();
            } catch(Exception exception) {

            }
            nameTextField.clear();
            raceTextField.clear();
            genderComboBox.getSelectionModel().clearSelection();
            weightTextField.clear();
            heightTextField.clear();
            ageTextField.clear();
            photo = null;
        }
    }

    public void quit() {
        Stage stage = (Stage) choosePhotoButton.getScene().getWindow();
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje ime nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkRace() {
        if("".equals(raceTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje rasa nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkGender() {
        if(genderComboBox.getSelectionModel().getSelectedItem() == null) {
            try {
                new AlertBoxForm("Pol psa nije izabran!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkWeight() {
        if("".equals(weightTextField.getText()) || !checkNumber(weightTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje težina nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkHeight() {
        if("".equals(heightTextField.getText()) || !checkNumber(heightTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje visina nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkAge() {
        if("".equals(ageTextField.getText()) || !checkNumber(ageTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje starost nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkPhoto() {
        if(photo == null) {
            try {
                new AlertBoxForm("Fotografija psa nije izabrana!").display();
            } catch(Exception exception) {

            }
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
}
