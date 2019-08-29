package data.dao;

import data.dto.EmploymentContractDTO;
import data.dto.VeterinarianDTO;

public interface VeterinarinaDAO {
    public boolean insert(VeterinarianDTO veterinarian, EmploymentContractDTO contract);
}
