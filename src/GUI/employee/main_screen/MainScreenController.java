package GUI.employee.main_screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private Button addDogButton;
    @FXML
    private Button adoptDogButton;
    @FXML
    private Button addMedicineButton;
    @FXML
    //samo trenutno String radi probe
    private TableView<String> dogsTableView;


    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }
}
