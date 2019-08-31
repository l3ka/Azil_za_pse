package GUI.admin.select_account;

import data.dto.EmployeeDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SelectAccountController {

    private Stage stage;
    @FXML
    private TableView<EmployeeDTO> accountsTableView;
    @FXML
    private Button selectButton;
    @FXML
    private Button quitButton;

    public void initialize(Stage stage) {
        this.stage = stage;
    }
}
