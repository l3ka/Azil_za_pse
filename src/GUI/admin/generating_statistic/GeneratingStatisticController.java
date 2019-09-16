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
    private ComboBox<String> statisticEmployeeComboBox;
    @FXML
    private ComboBox<String> statisticOtherComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Button generateStatisticButton;
    @FXML
    private Button generateEmployeeStatisticButton;
    @FXML
    private Button generateOtherStatisticButton;
    @FXML
    private Button quitButton;

    private Stage stage;

    public void initialize(Stage stage) {
        try {
            this.stage = stage;
            statisticComboBox.getItems().addAll("Udomitelji", "Udmoljeni psi");
            statisticEmployeeComboBox.getItems().addAll("SVI zaposleni", "AKTIVNI zaposleni", "NEAKTIVNI zaposleni");
            statisticOtherComboBox.getItems().addAll("Lijekovi", "Nalazi", "Kavezi", "Udomitelji i psi", "Psi u kavezima");
            startDatePicker.getEditor().setEditable(false);
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
            generateEmployeeStatisticButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    generateEmployeeStatisticButton.fire();
                    e.consume();
                }
            });
            generateOtherStatisticButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    generateOtherStatisticButton.fire();
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
                    new FosterParentsStatisticResultForm(startDatePicker.getValue()).display();
                } else {
                    new AdoptedDogsStatisticResultForm(startDatePicker.getValue()).display();
                }
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateStatistic", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void quit() {
        try {
            stage.close();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - quit", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
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

    public void generateEmployeeStatistic() {
        try {
            String comboValue = statisticEmployeeComboBox.getSelectionModel().getSelectedItem();
            if (comboValue != null) {
                if (comboValue.equals("SVI zaposleni")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportEmployees("SVI");
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("AKTIVNI zaposleni")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportEmployees("AKTIVNI");
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("NEAKTIVNI zaposleni")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportEmployees("NEAKTIVNI");
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
            }
            else {
                displayAlertBox("Nije izabran parametar!");
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateEmployeeStatistic", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

    public void generateOtherStatistic() {
        try {
            String comboValue = statisticOtherComboBox.getSelectionModel().getSelectedItem();
            if (comboValue != null) {
                if (comboValue.equals("Lijekovi")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportMedicine();
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("Nalazi")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportMedicalReports();
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("Kavezi")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportCages();
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("Udomitelji i psi")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportFosterDogJoin();
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
                else if (comboValue.equals("Psi u kavezima")) {
                    AzilUtilities.getDAOFactory().getPdfExporterDAO().exportDogInCage();
                    displayAlertBox("Izvještaj je uspješno generisan i eksportovan u pdf dokument!");
                }
            }
            else {
                displayAlertBox("Nije izabran parametar!");
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("GeneratingStatisticController - generateOtherStatistic", new Date(Calendar.getInstance().getTime().getTime()),  ex.fillInStackTrace().toString()));
        }
    }

}
