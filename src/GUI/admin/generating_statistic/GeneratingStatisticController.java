package GUI.admin.generating_statistic;

import GUI.admin.statistic_result.adopted_dogs.AdoptedDogsStatisticResultForm;
import GUI.admin.statistic_result.foster_parents.FosterParentsStatisticResultForm;
import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GeneratingStatisticController {

    @FXML
    private ComboBox<String> statisticComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Button generateStatisticButton;
    @FXML
    private Button quitButton;

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        statisticComboBox.getItems().addAll("Udomitelji", "Udmoljeni psi");
        initButtonEvent();
    }

    private void initButtonEvent() {
        generateStatisticButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                generateStatisticButton.fire();
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
