package data.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CageDTO {

    private Integer cageId;
    private String name;
    private int capacity;
    private int fullCapacity;
    private HashSet<DogInCageDTO> dogsInCage;

    public CageDTO() {}

    public CageDTO(Integer cageId, String name, int capacity, int fullCapacity) {
        this.cageId = cageId;
        this.name = name;
        this.capacity = capacity;
        this.fullCapacity = fullCapacity;
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFullCapacity() {
        return fullCapacity;
    }

    public void setFullCapacity(int fullCapacity) {
        this.fullCapacity = fullCapacity;
    }


    @Override
    public String toString() {
        return name;
    }

}
