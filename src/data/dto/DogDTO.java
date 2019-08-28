package data.dto;

import java.io.File;
import java.sql.Date;

public class DogDTO {
    private Integer Id;
    private String gender;
    private String name;
    private String breed;
    private int height;
    private double weigth;
    private Date dateOfBirth;
    private String image;

    public DogDTO() {
    }

    public DogDTO(Integer Id, String gender, String name, String breed, int height, double weigth, Date dateOfBirth, String image) {
        this.Id = Id;
        this.gender = gender;
        this.name = name;
        this.breed = breed;
        this.height = height;
        this.weigth = weigth;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }

    public Integer getId() { return  Id; }

    public void setId(int Id) { this.Id = Id; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}