package data.dao;

import data.dto.DogDTO;

import java.util.List;

public interface DogDAO {
    public List<DogDTO> dogs();

    public boolean insert(DogDTO dogDTO);

    public boolean update(DogDTO dogDTO);

    public boolean delete(int Id);
}
