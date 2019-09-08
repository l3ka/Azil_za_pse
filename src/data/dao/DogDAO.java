package data.dao;

import data.dto.DogDTO;
import java.time.LocalDate;
import java.util.List;

public interface DogDAO {

    DogDTO getLastDog();

    List<DogDTO> getAdoptedDogs(LocalDate dateFrom);

    List<DogDTO> dogs();

    DogDTO getByID(int ID);

    List<DogDTO> dogsByBreed(String breed);

    boolean insert(DogDTO dogDTO);

    boolean update(DogDTO dogDTO);

    boolean delete(DogDTO dog);

}
