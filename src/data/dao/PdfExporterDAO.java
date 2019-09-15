package data.dao;

import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import javafx.scene.control.TableView;

public interface PdfExporterDAO {

    void exportEmployees(String condition);

    void exportMedicine();

    void exportMedicalReports();

    void exportCages();

    void exportFosters(TableView<FosterParentDTO> fosterParentsTableView, String datumOd);

    void exportAdoptedDogs(TableView<DogDTO> dogsTableView, String datumOd);

    void exportFosterDogJoin();

    void exportDogInCage();



}
