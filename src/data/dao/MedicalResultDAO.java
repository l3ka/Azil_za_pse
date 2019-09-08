package data.dao;

import data.dto.DogDTO;
import data.dto.MedicalResultDTO;
import data.dto.VeterinarianDTO;
import java.util.List;

public interface MedicalResultDAO {

    boolean insert(MedicalResultDTO medicalResult);

    List<MedicalResultDTO> medicalResults(VeterinarianDTO veterinarian);

    List<MedicalResultDTO> medicalResults(DogDTO dog);

    List<MedicalResultDTO> medicalResults(DogDTO dog, VeterinarianDTO veterinarian);

    MedicalResultDTO getById(int Id);

    boolean update(MedicalResultDTO medicalResult);

    boolean update(MedicalResultDTO medicalResult, int dogId, String veterinarianJMB);

    boolean delete(int Id);

}