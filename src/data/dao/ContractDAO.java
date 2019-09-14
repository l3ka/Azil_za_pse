package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import java.util.List;

public interface ContractDAO {

    boolean insert(EmploymentContractDTO contract, EmployeeDTO employee);

    List<EmploymentContractDTO> contractsForEmployee(EmployeeDTO employee);

    EmploymentContractDTO contractById(int ContractId);

    boolean delete(EmploymentContractDTO contract);

    boolean update(EmploymentContractDTO contract);

}
