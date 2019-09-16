package data.dao;

import data.dto.AdoptingDogDTO;
import java.util.List;

public interface AdoptingDogDAO {

    List<AdoptingDogDTO> getAllAdoptings();

    boolean update(AdoptingDogDTO adoptingDog);

}
