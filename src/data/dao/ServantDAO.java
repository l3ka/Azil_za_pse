package data.dao;

import data.dto.EmploymentContractDTO;
import data.dto.ServantDTO;

import java.util.List;

public interface ServantDAO {
    public boolean insert(ServantDTO servant, EmploymentContractDTO contract);

    public List<ServantDTO> servants();

    public ServantDTO getByUsername(String username);

    public boolean update(ServantDTO servant);

    public boolean updateWithJMB(ServantDTO servant, String oldJMB);

    public boolean delete(ServantDTO servant);

    public boolean exists(ServantDTO servant);

    public ServantDTO login(String username, String password);
}
