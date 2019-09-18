package data.dao.mysql;

import data.dao.DogInCageDAO;
import data.dto.CageDTO;
import data.dto.DogDTO;
import data.dto.DogInCageDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLDogInCage implements DogInCageDAO {

    @Override
    public boolean insert(DogInCageDTO dogInCage) {
        boolean retVal = true;

        Connection conn = null;
        CallableStatement cs = null;
        String query = "call add_dog_in_cage(?, ?, ?, ?)";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);
            cs.setTimestamp(1, dogInCage.getDateForm());
            cs.setInt(2, dogInCage.getCage().getId());
            cs.setInt(3, dogInCage.getDog().getDogId());
            cs.setTimestamp(4, dogInCage.getDateTo());
            retVal = !cs.execute();
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(cs);
        }

        return retVal;
    }

    @Override
    public List<DogInCageDTO> dogInCages(CageDTO cage){
        List<DogInCageDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM kavez_pas WHERE IdKaveza=? AND Do IS NULL";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cage.getId());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new DogInCageDTO(
                        AzilUtilities.getDAOFactory().getDogDAO().getByID(rs.getInt("IdPsa")), cage,
                        rs.getTimestamp("Od"),
                        rs.getTimestamp("Do")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - dogInCages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
                        "IdKavez=?," +
                        "IdPsa=?," +
                        "Do=? " +
                       "WHERE Od=? AND IdKaveza=? AND IdPsa=?";
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean delete(Timestamp dateFrom, int cageId, int dogId){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM kavez_pas " +
                       "WHERE Od=?  AND IdKaveza=? AND IdPsa=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setTimestamp(1, dateFrom);
            ps.setInt(2, cageId);
            ps.setInt(3, dogId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public CageDTO getCage(DogDTO dog) {
        CageDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT kp.IdKaveza IdKaveza, k.Naziv Naziv, k.Kapacitet Kapacitet, k.Ukupno Ukupno FROM kavez_pas kp " +
                       "JOIN kavez k " +
                       "ON kp.IdKaveza = k.IdKaveza " +
                       "WHERE Do IS NULL AND IdPsa = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dog.getDogId());

            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new CageDTO(
                        rs.getInt("IdKaveza"),
                        rs.getString("Naziv"),
                        rs.getInt("Kapacitet"),
                        rs.getInt("Ukupno")
                );
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - getCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public DogInCageDTO getDogInCage(int IdCage, int IdDog) {
        DogInCageDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM kavez_pas " +
                       "WHERE IdKaveza = ? AND IdPsa = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, IdCage);
            ps.setInt(2, IdDog);

            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new DogInCageDTO(
                        AzilUtilities.getDAOFactory().getDogDAO().getByID(rs.getInt("IdPsa")),
                        AzilUtilities.getDAOFactory().getCageDAO().getById(rs.getInt("IdKaveza")),
                        rs.getTimestamp("Od"),
                        rs.getTimestamp("Do")
                );
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogInCage - getDogInCage", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

}
