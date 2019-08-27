package data.dto;

import java.io.File;
import java.util.Date;

public class DogDTO {
    private String gender;
    private String name;
    private String breed;
    private double height;
    private double weigth;
    private Date dateOfBirth;
    private File image;

    public DogDTO() {
    }

    public DogDTO(String gender, String name, String breed, double height, double weigth, Date dateOfBirth, File image) {
        this.gender = gender;
        this.name = name;
        this.breed = breed;
        this.height = height;
        this.weigth = weigth;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }

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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}