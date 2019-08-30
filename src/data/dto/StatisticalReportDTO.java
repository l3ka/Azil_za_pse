package data.dto;
import java.io.Serializable;
import java.io.File;
import java.sql.Date;

public class StatisticalReportDTO implements Serializable {
    private Date creationDate;
    private int serialNumber;
    private File report;

    public StatisticalReportDTO() {
    }

    public StatisticalReportDTO(Date creationDate, int serialNumber, File report) {
        this.creationDate = creationDate;
        this.serialNumber = serialNumber;
        this.report = report;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public File getReport() {
        return report;
    }

    public void setReport(File report) {
        this.report = report;
    }
}