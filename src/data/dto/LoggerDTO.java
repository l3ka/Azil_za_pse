package data.dto;

import java.sql.Date;

public class LoggerDTO {

    private String username;
    private Date date;
    private String description;

    public LoggerDTO() {}

    public LoggerDTO(String username, Date date, String description) {
        this.username = username;
        this.date = date;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
