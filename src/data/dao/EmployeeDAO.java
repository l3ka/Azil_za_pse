package data.dao;

import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

public interface EmployeeDAO {

    EmployeeDTO getEmployeeByJMB(String jmb);

    boolean insert(EmployeeDTO employee, EmploymentContractDTO contract);

    boolean update(EmployeeDTO employee);

    boolean updateWithJMB(EmployeeDTO employee, String oldJMB);

    boolean delete(EmployeeDTO employee);

    boolean activate(EmployeeDTO employee);

    boolean exists(EmployeeDTO employee);

    boolean exists(String username, String JMB);

}
