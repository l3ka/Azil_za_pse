package data.dao;

import data.dto.MedicineDTO;
import java.util.List;

public interface MedicineDAO {

    boolean insert(MedicineDTO medicine);

    MedicineDTO getById(int id);

    boolean update(MedicineDTO medicine);

    boolean delete(int Id);

    List<MedicineDTO> medicines();

}
