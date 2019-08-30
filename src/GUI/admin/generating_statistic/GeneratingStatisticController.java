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
            try {
                new AlertBoxForm("Uspješno generisana statistika!").display(); // ovo nece bas ovako
            } catch(Exception exception) {

            }
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkStartDate() {
        if(startDatePicker.getValue() == null) {
            try {
                new AlertBoxForm("Nije izabran početni datum!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkEndDate() {
        if(endDatePicker.getValue() == null) {
            try {
                new AlertBoxForm("Nije izabran krajnji datum!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkParameter() {
        if(statisticComboBox.getSelectionModel().getSelectedItem() == null) {
            try {
                new AlertBoxForm("Nije izabran parametar!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }
}
