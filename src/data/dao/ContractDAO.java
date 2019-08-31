package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

import java.util.List;

public interface ContractDAO {
    public boolean insert(EmploymentContractDTO contract, EmployeeDTO employee);

    public List<EmploymentContractDTO> contractsForEmployee(EmployeeDTO employee);

    public EmploymentContractDTO contarctById(int ContractId);

    public boolean delete(EmploymentContractDTO contract);

    public boolean update(EmploymentContractDTO contract);
}
