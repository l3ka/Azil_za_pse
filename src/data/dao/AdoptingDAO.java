package data.dao;

import data.dto.AdoptingDTO;
import data.dto.FosterParentDTO;
import java.sql.Date;
import java.util.List;

public interface AdoptingDAO {

    boolean insert(FosterParentDTO fosterParent, AdoptingDTO adopting);

    List<AdoptingDTO> getAdoptingForFosterParent(FosterParentDTO fosterParent);

    boolean update(FosterParentDTO fosterParentDTO, AdoptingDTO adopting, Date dateFrom, int dogId, String JMB);

    boolean delete(Date dateFrom, int dogId, String JMB);

}
