package GUI.admin.generating_statistic;

import GUI.admin.statistic_result.adopted_dogs.AdoptedDogsStatisticResultForm;
import GUI.admin.statistic_result.foster_parents.FosterParentsStatisticResultForm;
import GUI.alert_box.AlertBoxForm;
import data.dto.LoggerDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.AzilUtilities;

import java.sql.Date;
import java.util.Calendar;

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
        try {
            this.stage = stage;
            statisticComboBox.getItems().addAll("Udomitelji", "Udmoljeni psi");
            initButtonEvent();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - initialize", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    private void initButtonEvent() {
        try {
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
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - initButtonEvent", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void generateStatistic() {
        try {
            if(checkParameter() && checkStartDate()) {
                if("Udomitelji".equals(statisticComboBox.getSelectionModel().getSelectedItem())) {
                    try {
                        new FosterParentsStatisticResultForm(startDatePicker.getValue()).display();
                    } catch (Exception ex) {
                        AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateStatistic", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
                    }
                } else {
                    try {
                        new AdoptedDogsStatisticResultForm(startDatePicker.getValue()).display();
                    } catch(Exception ex) {
                        AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateStatistic", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
                    }
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateStatistic", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkStartDate() {
        try {
            if(startDatePicker.getValue() == null) {
                displayAlertBox("Nije izabran početni datum!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - checkStartDate", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private boolean checkParameter() {
        try {
            if(statisticComboBox.getSelectionModel().getSelectedItem() == null) {
                displayAlertBox("Nije izabran parametar!");
                return false;
            }
            return true;
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - checkParameter", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
            return false;
        }
    }

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - displayAlertBox", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

}
