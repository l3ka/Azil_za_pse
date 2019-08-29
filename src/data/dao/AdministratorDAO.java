package data.dao;

import data.dto.AdministratorDTO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

import java.sql.Date;

public interface AdministratorDAO {
    public boolean insert(AdministratorDTO administrator, EmploymentContractDTO contract);
}
