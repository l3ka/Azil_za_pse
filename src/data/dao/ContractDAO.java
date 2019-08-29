package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

public interface ContractDAO {
    public boolean insert(EmploymentContractDTO contract, EmployeeDTO employee);
}
