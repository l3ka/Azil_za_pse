package data.dao;

import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import javafx.scene.control.TableView;

public interface PdfExporterDAO {

    void exportEmployees();

    void exportMedicine();

    void exportMedicalReports();

    void exportFosters(TableView<FosterParentDTO> fosterParentsTableView);

    void exportAdoptedDogs(TableView<DogDTO> dogsTableView);

}
