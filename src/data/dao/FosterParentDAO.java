package data.dao;

import data.dto.FosterParentDTO;
import java.time.LocalDate;
import java.util.List;

public interface FosterParentDAO {

    List<FosterParentDTO> fosterParents();

    List<FosterParentDTO> fosterParents(LocalDate dateFrom);

    FosterParentDTO getByJMB(String JMB);

    boolean insert(FosterParentDTO fosterParent);

    boolean update(FosterParentDTO fosterParent);

    boolean updateWithJMB(FosterParentDTO fosterParent, String oldJMB);

    boolean delete(String JMB);

}
