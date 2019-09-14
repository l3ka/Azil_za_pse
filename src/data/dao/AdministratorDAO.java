package data.dao;

import data.dto.AdministratorDTO;
import data.dto.EmploymentContractDTO;
import java.util.List;

public interface AdministratorDAO {

    boolean insert(AdministratorDTO administrator, EmploymentContractDTO contract);

    List<AdministratorDTO> adminstartors();

    List<AdministratorDTO> adminstartorsDeactivated();

    AdministratorDTO getByUsername(String username);

    boolean update(AdministratorDTO administrator);

    boolean updateWithJMB(AdministratorDTO administrator, String oldJMB);

    boolean delete(AdministratorDTO administrator);

    boolean exists(AdministratorDTO administrator);

    boolean exists(String username, String password);

    AdministratorDTO login(String username, String password);

}
