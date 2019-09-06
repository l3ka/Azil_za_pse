package data.dao;

import data.dto.MedicineDTO;

import java.security.MessageDigest;
import java.util.List;

public interface MedicineDAO {
    public boolean insert(MedicineDTO medicine);

    public MedicineDTO getById(int id);

    public boolean update(MedicineDTO medicine);

    public boolean delete(int Id);

    public List<MedicineDTO> medicines();
}
