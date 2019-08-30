package data.dao;

import data.dto.AdministratorDTO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

import java.sql.Date;
import java.util.List;

public interface AdministratorDAO {
    public boolean insert(AdministratorDTO administrator, EmploymentContractDTO contract);

    public List<AdministratorDTO> adminstartors();

    public AdministratorDTO getByUsername(String username);

    public boolean update(AdministratorDTO administrator);

    public boolean updateWithJMB(AdministratorDTO administrator, String oldJMB);
}
