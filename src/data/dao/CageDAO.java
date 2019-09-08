package data.dao;

import data.dto.CageDTO;
import java.util.List;

public interface CageDAO {

    List<CageDTO> cages();

    CageDTO getById(int Id);

    boolean insert(CageDTO cage);

    boolean update(CageDTO cage);

    boolean delete(int cageID);

}
