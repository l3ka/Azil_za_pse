package data.dao;

import data.dto.DogDTO;

import java.util.List;

public interface DogDAO {

    public DogDTO getLastDog();

    public List<DogDTO> dogs();

    public DogDTO getByID(int ID);

    public boolean insert(DogDTO dogDTO);

    public boolean update(DogDTO dogDTO);

    public boolean delete(DogDTO dog);
}
