package GUI.admin.generating_statistic;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class GeneratingStatisticController {

    @FXML private ComboBox<String> statisticComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        statisticComboBox.getItems().addAll("Pol", "Rasa", "Visina", "Težina", "Starost");
    }

    public void generateStatistic() {
        if(checkParameter() && checkStartDate() && checkEndDate()) {
            displayAlertBox("Uspješno generisana statistika!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkStartDate() {
        if(startDatePicker.getValue() == null) {
            displayAlertBox("Nije izabran početni datum!");
            return false;
        }
        return true;
    }

    private boolean checkEndDate() {
        if(endDatePicker.getValue() == null) {
            displayAlertBox("Nije izabran krajnji datum!");
            return false;
        }
        return true;
    }

    private boolean checkParameter() {
        if(statisticComboBox.getSelectionModel().getSelectedItem() == null) {
            displayAlertBox("Nije izabran parametar!");
            return false;
        }
        return true;
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception exception) {

        }
    }
}
