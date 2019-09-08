package data.dao;

import data.dto.DogDTO;

import java.time.LocalDate;
import java.util.List;

public interface DogDAO {

    public DogDTO getLastDog();

    public List<DogDTO> getAdoptedDogs(LocalDate dateFrom);

    public List<DogDTO> dogs();

    public DogDTO getByID(int ID);

    public List<DogDTO> dogsByBreed(String breed);

    public boolean insert(DogDTO dogDTO);

    public boolean update(DogDTO dogDTO);

    public boolean delete(DogDTO dog);
}
