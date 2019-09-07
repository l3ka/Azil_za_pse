package data.dao;

import data.dto.TakingMedicineDTO;

public interface TakingMedicineDAO {

    public boolean insert(TakingMedicineDTO takingMedicine);
    public boolean update(TakingMedicineDTO takingMedicine);
    public boolean delete(int medicineId);
}
