package data.dao.mysql;

import data.dao.TakingMedicineDAO;
import data.dto.LoggerDTO;
import data.dto.TakingMedicineDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class MySQLTakingMedicineDAO implements TakingMedicineDAO {

    @Override
    public boolean insert(TakingMedicineDTO takingMedicine) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO uzimanjelijeka " +
                       "VALUES (?, ?, ?, ?, ?)";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setTimestamp(1, takingMedicine.getDate());
            ps.setString(2, takingMedicine.getVeterinarian().getJMB());
            ps.setInt(3, takingMedicine.getMedicine().getMedicineId());
            ps.setInt(4, takingMedicine.getMedicalResult().getMedicalResultId());
            ps.setInt(5, takingMedicine.getQuantity());
            retVal = ps.executeUpdate() == 1;
        } catch(SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLTakingMedicineDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public boolean update(TakingMedicineDTO takingMedicine) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE uzimanjelijeka SET " +
                        "DatumUzimanja=?, " +
                        "VeterinarJMBG=?, " +
                        "IdLijeka=?, " +
                        "IdNalaza=?, " +
                        "Kolicina=? " +
                       "WHERE IdLijeka=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setTimestamp(1, takingMedicine.getDate());
            ps.setString(2, takingMedicine.getVeterinarian().getJMB());
            ps.setInt(3, takingMedicine.getMedicine().getMedicineId());
            ps.setInt(4, takingMedicine.getMedicalResult().getMedicalResultId());
            ps.setInt(5, takingMedicine.getQuantity());
            ps.setInt(6, takingMedicine.getMedicine().getMedicineId());

            retVal = ps.executeUpdate() == 1;
        } catch(SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLTakingMedicineDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

     @Override
    public boolean delete(int medicineId) {
        boolean retVal = false;
         Connection conn = null;
         PreparedStatement ps = null;

         String query = "DELETE FROM uzimanjelijeka " +
                        "WHERE IdLijeka=? ";

         try {
             conn = ConnectionPool.getInstance().checkOut();
             ps = conn.prepareStatement(query);
             ps.setInt(1, medicineId);

             retVal = ps.executeUpdate() == 1;
         } catch(SQLException ex) {
             AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLTakingMedicineDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
         } finally {
             ConnectionPool.getInstance().checkIn(conn);
             DBUtilities.getInstance().close(ps);
         }
         return retVal;
     }

}
