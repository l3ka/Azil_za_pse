package data.dao;

import data.dto.EmploymentContractDTO;
import data.dto.VeterinarianDTO;
import java.util.List;

public interface VeterinarianDAO {

    boolean insert(VeterinarianDTO veterinarian, EmploymentContractDTO contract);

    List<VeterinarianDTO> veterinarians();

    List<VeterinarianDTO> veterinariansDeactivated();

    VeterinarianDTO getByUsername(String username);

    boolean update(VeterinarianDTO veterinarian);

    boolean updateWithJMB(VeterinarianDTO veterinarian, String oldJMB);

    boolean delete(VeterinarianDTO veterinarian);

    boolean exists(VeterinarianDTO veterinarian);

    boolean exists(String username, String password);

    VeterinarianDTO login(String username, String password);

}
