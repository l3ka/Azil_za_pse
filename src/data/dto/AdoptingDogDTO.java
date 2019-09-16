package data.dto;

import java.sql.Date;
import java.util.Objects;

public class AdoptingDogDTO {

    private Date dateFrom;
    private int idDog;
    private String jmbFosterParent;
    private Date dateTo;

    public AdoptingDogDTO() {}

    public AdoptingDogDTO(Date dateFrom, int idDog, String jmbFosterParent, Date dateTo) {
        this.dateFrom = dateFrom;
        this.idDog = idDog;
        this.jmbFosterParent = jmbFosterParent;
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public int getIdDog() {
        return idDog;
    }

    public void setIdDog(int idDog) {
        this.idDog = idDog;
    }

    public String getJmbFosterParent() {
        return jmbFosterParent;
    }

    public void setJmbFosterParent(String jmbFosterParent) {
        this.jmbFosterParent = jmbFosterParent;
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
        return Objects.equals(idDog, that.idDog) &&
                Objects.equals(jmbFosterParent, that.jmbFosterParent) &&
                Objects.equals(dateFrom, that.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, idDog, jmbFosterParent, dateFrom);
    }

}
