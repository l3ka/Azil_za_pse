package data.dao;

import data.dto.CageDTO;

import java.util.List;

public interface CageDAO {
    public List<CageDTO> cages();

    public CageDTO getById(int Id);

    public boolean insert(CageDTO cage);

    public boolean update(CageDTO cage);

    public boolean delete(int cageID);
}
