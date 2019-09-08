package data.dao;

import data.dto.TakingMedicineDTO;

public interface TakingMedicineDAO {

    boolean insert(TakingMedicineDTO takingMedicine);

    boolean update(TakingMedicineDTO takingMedicine);

    boolean delete(int medicineId);

}
