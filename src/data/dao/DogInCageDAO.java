package data.dao;

import data.dto.CageDTO;
import data.dto.DogInCageDTO;

import java.sql.Date;
import java.util.List;

public interface DogInCageDAO {

    public boolean insert(DogInCageDTO dogInCage);

    public List<DogInCageDTO> dogInCages(CageDTO cage);

    public boolean update(DogInCageDTO dogInCage, Date dateFrom, int cageId, int dogId);

    public boolean delete(Date dateFrom, int cageId, int dogId);
}
