package data.dto;

public class CageDTO {
    private int ID;
    private int capacity;

    public CageDTO(){
    }

    public CageDTO(int ID, int capacity) {
        this.ID = ID;
        this.capacity = capacity;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
