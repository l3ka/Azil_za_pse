package data.dao;

import data.dto.EmployeeContractDTO;
import java.util.List;

public interface EmployeeContractDAO {

    List<EmployeeContractDTO> getAllEmployeeContract();

    boolean update(EmployeeContractDTO employeeContract);

}
