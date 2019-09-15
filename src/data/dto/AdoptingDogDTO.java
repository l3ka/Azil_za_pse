package data.dto;

import java.sql.Date;
import java.util.Objects;

public class AdoptingDogDTO {

    private Date dateFrom;
    private DogDTO dog;
    private FosterParentDTO fosterParent;
    private Date dateTo;

    public AdoptingDogDTO() {}

    public AdoptingDogDTO(Date dateFrom, DogDTO dog, FosterParentDTO fosterParent, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dog = dog;
        this.fosterParent = fosterParent;
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public DogDTO getDog() {
        return dog;
    }

    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    public FosterParentDTO getFosterParent() {
        return fosterParent;
    }

    public void setFosterParent(FosterParentDTO fosterParent) {
        this.fosterParent = fosterParent;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptingDogDTO that = (AdoptingDogDTO) o;
        return Objects.equals(dog, that.dog) &&
                Objects.equals(fosterParent, that.fosterParent) &&
                Objects.equals(dateFrom, that.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dog, fosterParent, dateFrom);
    }

}
