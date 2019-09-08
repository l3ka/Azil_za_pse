package GUI.admin.generating_statistic;

import GUI.admin.statistic_result.adopted_dogs.AdoptedDogsStatisticResultForm;
import GUI.admin.statistic_result.foster_parents.FosterParentsStatisticResultForm;
import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class GeneratingStatisticController {

    @FXML private ComboBox<String> statisticComboBox;
    @FXML private DatePicker startDatePicker;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        statisticComboBox.getItems().addAll("Udomitelji", "Udmoljeni psi");
    }

    public void generateStatistic() {
        if(checkParameter() && checkStartDate()) {
            if("Udomitelji".equals(statisticComboBox.getSelectionModel().getSelectedItem())) {
                try {
                    new FosterParentsStatisticResultForm(startDatePicker.getValue()).display();
                } catch (Exception ex) {

                }
            } else {
                try {
                    new AdoptedDogsStatisticResultForm(startDatePicker.getValue()).display();
                } catch(Exception ex) {

                }
            }
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
