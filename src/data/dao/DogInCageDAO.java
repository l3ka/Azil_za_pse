package data.dao;

import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface DogInCageDAO {

    boolean insert(DogInCageDTO dogInCage);

    List<DogInCageDTO> dogInCages(CageDTO cage);

    boolean update(DogInCageDTO dogInCage, Date dateFrom, int cageId, int dogId);

    boolean delete(Timestamp dateFrom, int cageId, int dogId);

    CageDTO getCage(DogDTO dog);

    public DogInCageDTO getDogInCage(int IdCage, int IdDog);


}
