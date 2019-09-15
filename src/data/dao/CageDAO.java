package data.dao;

import data.dto.CageDTO;
import java.util.List;

public interface CageDAO {

    List<CageDTO> cages();

    CageDTO getById(int Id);

    List<CageDTO> getByName(String name);

    boolean insert(CageDTO cage);

    boolean update(CageDTO cage);

    boolean delete(int cageID);

}
