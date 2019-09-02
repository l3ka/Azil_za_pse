package data.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CageDTO {
    private Integer cageId;
    private int capacity;
    private HashSet<DogInCageDTO> dogsInCage;

    public CageDTO(){
    }

    public CageDTO(Integer cageId, int capacity) {
        this.cageId = cageId;
        this.capacity = capacity;
        this.dogsInCage = new HashSet<>();
    }

    public List<DogInCageDTO> dogsInCage(){
        return new ArrayList<>(dogsInCage);
    }

    public void addDogInCage(DogInCageDTO dogInCage){
        dogsInCage.add(dogInCage);
    }

    public int getId() {
        return cageId;
    }

    public void setId(int cageId) {
        this.cageId = cageId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
