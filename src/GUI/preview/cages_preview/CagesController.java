package GUI.preview.cages_preview;

import GUI.adding_cage.AddingCageForm;
import GUI.alert_box.AlertBoxForm;
import GUI.decide_box.DecideBox;
import GUI.editing_cage.EditingCageForm;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CagesController {

    @FXML
    private TableView<CageDTO> cagesTableView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private HBox alert;
    @FXML
    private Label numberOfCagesLabel;
    @FXML
    private Label numberOfFreeSpotsLabel;
    @FXML
    private Circle circle0, circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9;
    @FXML
    private ListView<String> dogsListView;

    private Stage stage;
    private List<CageDTO> listOfCages;
    private ArrayList<Circle> circles = new ArrayList<>();
    private HashMap<CageDTO, List<DogDTO>> dogsInCage = new HashMap<>();
    private Paint green = Paint.valueOf("#00fc65");
    private Paint gray = Paint.valueOf("#575757");
    private Paint red = Paint.valueOf("#ff0000");

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            circles.add(circle0);
            circles.add(circle1);
            circles.add(circle2);
            circles.add(circle3);
            circles.add(circle4);
            circles.add(circle5);
            circles.add(circle6);
            circles.add(circle7);
            circles.add(circle8);
            circles.add(circle9);
            cagesTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
            cagesTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("capacity"));
            cagesTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fullCapacity"));
            cagesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                dogsListView.getItems().clear();
                dogsListView.refresh();
                deleteButton.setDisable(false);
                alert.setVisible(false);
                if (newValue == null)  {
                    return;
                }
                List<DogDTO> dogs;
                if (dogsInCage.containsKey(newValue)) {
                    dogs = dogsInCage.get(newValue);
                }
                else {
                    List<DogInCageDTO> dogsInCageList = AzilUtilities.getDAOFactory().getDogInCageDAO().dogInCages(newValue);
                    dogs = dogsInCageList.stream().map((dogInCageDTO -> dogInCageDTO.getDog())).collect(Collectors.toList());
                    if (!dogs.isEmpty()) {
                        dogsInCage.put(newValue, dogs);
                    }
                }
                if (dogs!= null && !dogs.isEmpty()) {
                    for (DogDTO dog : dogs) {
                        dogsListView.getItems().add(dog.toString());
                    }
                    deleteButton.setDisable(true);
                    alert.setVisible(true);
                }
                dogsListView.refresh();
                refreshCircles();

                Double percentage = (double)(newValue.getCapacity()) / (newValue.getFullCapacity());
                percentage*=10;
                int number = 10 - percentage.intValue();
                if (number == 10) {
                    for (Circle circle : circles) {
                        circle.setFill(red);
                    }
                    return;
                }
                for (int counter = 0; counter < number; counter ++) {
                    circles.get(counter).setFill(gray);
                }
                if (number > 7 || newValue.getCapacity() == 1) {
                    circle7.setFill(red);
                    circle8.setFill(red);
                    circle9.setFill(red);
                    return;
                }

            });

            displayCages();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - initialize", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void addCage() {
        try {
            new AddingCageForm().display();
            displayCages();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - addCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void updateCage() {
        try {
            if (checkSelectedCage()) {
                new EditingCageForm(cagesTableView.getSelectionModel().getSelectedItem()).display();
                displayCages();
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - updateCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void deleteCage() {
        try {
            if (checkSelectedCage()) {
                if (new DecideBox("Da li ste sigurni da želite da obrišete kavez?").display()) {
                    if (AzilUtilities.getDAOFactory().getCageDAO().delete(cagesTableView.getSelectionModel().getSelectedItem().getId())) {
                        displayAlertBox("Kavez je uspješno obrisan!");
                    } else {
                        displayAlertBox("Desila se greška prilikom brisanja kaveza!");
                    }
                    displayCages();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - deleteCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void search() {
        try {
            List<CageDTO> filteredList = listOfCages.stream()
                    .filter(cageDTO -> cageDTO.getName().toUpperCase().contains(nameTextField.getText().toUpperCase()))
                    .collect(Collectors.toList());
            cagesTableView.getItems().clear();
            dogsListView.getItems().clear();
            dogsListView.refresh();
            deleteButton.setDisable(false);
            alert.setVisible(false);
            refreshCircles();
            for (CageDTO cage : filteredList) {
                cagesTableView.getItems().add(cage);
            }
            cagesTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - search", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void displayAllCages() {
        try {
            nameTextField.clear();
            cagesTableView.getItems().clear();
            dogsListView.getItems().clear();
            dogsListView.refresh();
            refreshCircles();
            deleteButton.setDisable(false);
            alert.setVisible(false);
            for (CageDTO cage : listOfCages) {
                cagesTableView.getItems().add(cage);
            }
            cagesTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayAllCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - quit", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }

    private void displayCages() {
        try {
            int numberOfFreeSpots = 0;
            cagesTableView.getItems().clear();
            cagesTableView.refresh();
            dogsListView.getItems().clear();
            dogsListView.refresh();
            refreshCircles();
            deleteButton.setDisable(false);
            alert.setVisible(false);
            listOfCages = AzilUtilities.getDAOFactory().getCageDAO().cages();
            numberOfCagesLabel.setText("Broj kaveza: " + listOfCages.size());
            for (CageDTO cage : listOfCages) {
                cagesTableView.getItems().add(cage);
                numberOfFreeSpots += cage.getCapacity();
            }
            numberOfFreeSpotsLabel.setText("Broj slobodnih mjesta: " + numberOfFreeSpots);
            cagesTableView.refresh();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - displayCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
    }


    private boolean checkSelectedCage() {
        try {
            if (cagesTableView.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran kavez!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("CagesController - checkSelectedCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void refreshCircles() {
        for (Circle circle : circles) {
            circle.setFill(green);
        }
    }

}