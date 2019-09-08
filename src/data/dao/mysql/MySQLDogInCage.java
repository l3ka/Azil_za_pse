package data.dao.mysql;

import data.dao.DogInCageDAO;
import data.dto.CageDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDogInCage implements DogInCageDAO {

    @Override
    public boolean insert(DogInCageDTO dogInCage) {
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO kavez_pas  "
                + "VALUES (?, ?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setTimestamp(1, dogInCage.getDateForm());
            ps.setInt(2, dogInCage.getCage().getId());
            ps.setInt(3, dogInCage.getDog().getDogId());
            ps.setTimestamp(4, dogInCage.getDateTo());

            retVal = ps.executeUpdate() == 1;
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public List<DogInCageDTO> dogInCages(CageDTO cage){
        List<DogInCageDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM kavez_pas WHERE KavezIdKaveza=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cage.getId());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new DogInCageDTO(
                        AzilUtilities.getDAOFactory().getDogDAO().getByID(rs.getInt("Pas_IdPsa")), cage,
                        rs.getTimestamp("datumOd"),
                        rs.getTimestamp("DatumDo")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    public boolean update(DogInCageDTO dogInCage, Date dateFrom, int cageId, int dogId){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE kavez_pas SET " +
                "Od=?, " +
                "Kavez_IdKavez=?," +
                "Pas_IdPsa=?," +
                "Do=? " +
                "WHERE Od=?  AND Kavez_IdKaveza=? AND Pas_IdPsa=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setTimestamp(1, dogInCage.getDateForm());
            ps.setInt(2, dogInCage.getCage().getId());
            ps.setInt(3, dogInCage.getDog().getDogId());
            ps.setTimestamp(4, dogInCage.getDateTo());

            ps.setDate(5, dateFrom);
            ps.setInt(6, cageId);
            ps.setInt(7, dogId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean delete(Date dateFrom, int cageId, int dogId){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM kavez_pas " +
                "WHERE Od=?  AND Kavez_IdKaveza=? AND Pas_IdPsa=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setDate(1, dateFrom);
            ps.setInt(2, cageId);
            ps.setInt(3, dogId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

}
