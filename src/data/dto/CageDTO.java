package data.dto;

public class CageDTO {
    private Integer cageId;
    private int capacity;

    public CageDTO(){
    }

    public CageDTO(Integer cageId, int capacity) {
        this.cageId = cageId;
        this.capacity = capacity;
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
