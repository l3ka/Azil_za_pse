package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

public interface EmployeeDAO {
    public boolean insert(EmployeeDTO employee, EmploymentContractDTO contract);

    public boolean update(EmployeeDTO employee);

    public boolean updateWithJMB(EmployeeDTO employee, String oldJMB);

    public boolean delete(EmployeeDTO employee);

    public boolean exists(EmployeeDTO employee);

    public boolean exists(String username, String JMB);

}
