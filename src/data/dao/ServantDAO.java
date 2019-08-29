package data.dao;

import data.dto.AdministratorDTO;
import data.dto.EmploymentContractDTO;
import data.dto.ServantDTO;

public interface ServantDAO {
    public boolean insert(ServantDTO servant, EmploymentContractDTO contract);
}
