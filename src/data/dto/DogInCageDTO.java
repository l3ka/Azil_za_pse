package data.dto;

import java.sql.Date;
import java.util.Objects;

public class DogInCageDTO {
    DogDTO dog;
    Date dateForm;
    Date dateTo;

    public DogInCageDTO(){}

    public DogInCageDTO(DogDTO dog, Date dateForm, Date dateTo) {
        this.dog = dog;
        this.dateForm = dateForm;
        this.dateTo = dateTo;
    }

    public DogDTO getDog() {
        return dog;
    }

    public void setDog(DogDTO dog) {
        this.dog = dog;
    }

    public Date getDateForm() {
        return dateForm;
    }

    public void setDateForm(Date dateForm) {
        this.dateForm = dateForm;
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
        DogInCageDTO that = (DogInCageDTO) o;
        return Objects.equals(dog, that.dog) &&
                Objects.equals(dateForm, that.dateForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dog, dateForm);
    }
}
