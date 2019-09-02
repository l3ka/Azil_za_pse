package data.dao;

import data.dto.AdoptingDTO;
import data.dto.FosterParentDTO;

import java.sql.Date;
import java.util.List;

public interface AdoptingDAO {

    public boolean insert(FosterParentDTO fosterParent, AdoptingDTO adopting);

    public List<AdoptingDTO> getAdoptingForFosterParent(FosterParentDTO fosterParent);

    public boolean update(FosterParentDTO fosterParentDTO, AdoptingDTO adopting, Date dateFrom, int dogId, String JMB);

    public boolean delete(Date dateFrom, int dogId, String JMB);
}
