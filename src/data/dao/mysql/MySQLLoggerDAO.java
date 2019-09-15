package data.dao.mysql;

import data.dao.LoggerDAO;
import data.dto.LoggerDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Calendar;

public class MySQLLoggerDAO implements LoggerDAO {

    @Override
    public void insert(LoggerDTO loggerDTO) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO logger values (?, ?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, loggerDTO.getUsername());
            ps.setDate(3, loggerDTO.getDate());
            ps.setString(4, loggerDTO.getDescription());
            ps.executeUpdate();
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLLoggerDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
    }

}
