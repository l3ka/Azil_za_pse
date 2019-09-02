package data.dao;

import data.dto.FosterParentDTO;

import java.util.List;

public interface FosterParentDAO {

    public List<FosterParentDTO> fosterParents();

    public FosterParentDTO getByJMB(String JMB);

    public boolean insert(FosterParentDTO fosterParent);

    public boolean update(FosterParentDTO fosterParent);

    public boolean updateWithJMB(FosterParentDTO fosterParent, String oldJMB);

    public boolean delete(String JMB);
}
