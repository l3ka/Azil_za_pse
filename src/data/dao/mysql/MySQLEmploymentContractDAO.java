package data.dao.mysql;

import data.dao.EmploymentContractDAO;
import data.dto.*;
import util.AzilUtilities;

import java.sql.*;
import java.util.Calendar;

public class MySQLEmploymentContractDAO implements EmploymentContractDAO {

    @Override
    public EmploymentContractDTO getById(int id) {
        EmploymentContractDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT IdUgovora, Pozicija, Aktivan, Plata " +
                       "FROM ugovororadu " +
                       "WHERE IdUgovora = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new EmploymentContractDTO(rs.getInt("IdUgovora"), rs.getInt("Aktivan"), rs.getString("Pozicija"), new Date(Calendar.getInstance().getTime().getTime()), new Date(Calendar.getInstance().getTime().getTime()),  rs.getDouble("Plata"));
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - getEmployeeByJMB", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

}
