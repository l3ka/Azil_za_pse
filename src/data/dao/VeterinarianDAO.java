package data.dao;

import data.dto.EmploymentContractDTO;
import data.dto.VeterinarianDTO;

import java.util.List;

public interface VeterinarianDAO {
    public boolean insert(VeterinarianDTO veterinarian, EmploymentContractDTO contract);

    public List<VeterinarianDTO> veterinarians();

    public VeterinarianDTO getByUsername(String username);

    public boolean update(VeterinarianDTO veterinarian);

    public boolean updateWithJMB(VeterinarianDTO veterinarian, String oldJMB);
}
