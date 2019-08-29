package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import enums.EmployeeType;

import java.sql.Date;

public interface EmployeeDAO {
    public boolean insert(EmployeeDTO employee, EmploymentContractDTO contract);
}
