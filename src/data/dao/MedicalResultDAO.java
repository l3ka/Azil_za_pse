package data.dao;

import data.dto.DogDTO;
import data.dto.MedicalResultDTO;
import data.dto.VeterinarianDTO;

import java.util.List;

public interface MedicalResultDAO {
    public boolean insert(MedicalResultDTO medicalResult);

    public List<MedicalResultDTO> medicalResults(VeterinarianDTO veterinarian);

    public List<MedicalResultDTO> medicalResults(DogDTO dog);

    public List<MedicalResultDTO> medicalResults(DogDTO dog, VeterinarianDTO veterinarian);

    public MedicalResultDTO getById(int Id);

    public boolean update(MedicalResultDTO medicalResult);

    public boolean update(MedicalResultDTO medicalResult, int dogId, String veterinarianJMB);

    public boolean delete(int Id);
}