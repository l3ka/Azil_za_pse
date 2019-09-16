package data.dao;

import data.dto.EmploymentContractDTO;
import data.dto.ServantDTO;
import java.util.List;

public interface ServantDAO {

    boolean insert(ServantDTO servant, EmploymentContractDTO contract);

    List<ServantDTO> servants();

    List<ServantDTO> servantsDeactivated();

    ServantDTO getByUsername(String username);

    boolean update(ServantDTO servant);

    boolean updateWithJMB(ServantDTO servant, String oldJMB);

    boolean delete(ServantDTO servant);

    boolean exists(ServantDTO servant);

    boolean exists(String username, String password);

    ServantDTO login(String username, String password);

}
