package data.dto;

import java.sql.Date;
import java.util.Objects;

public class AdoptingDTO {

    private DogDTO dog;
    private Date dateFrom;
    private Date dateTo;

    public AdoptingDTO() {}

    public AdoptingDTO(DogDTO dog, Date dateFrom, Date dateTo) {
        this.dog = dog;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public DogDTO getDog() {
        return dog;
    }

    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
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
        AdoptingDTO that = (AdoptingDTO) o;
        return Objects.equals(dog, that.dog) &&
                Objects.equals(dateFrom, that.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dog, dateFrom);
    }

}
