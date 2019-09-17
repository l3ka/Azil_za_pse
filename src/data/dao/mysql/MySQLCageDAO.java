package data.dao.mysql;

import data.dao.CageDAO;
import data.dto.CageDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLCageDAO implements CageDAO {

    @Override
    public List<CageDTO> cages(){
        List<CageDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM kavez";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new CageDTO(
                        rs.getInt("IdKaveza"),
                        rs.getString("Naziv"),
                        rs.getInt("Kapacitet"),
                        rs.getInt("Ukupno")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - cages", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public CageDTO getById(int Id){
        CageDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM kavez WHERE IdKaveza=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Id);
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - getById", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<CageDTO> getByName(String name){
        ArrayList<CageDTO> retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM kavez WHERE Naziv LIKE ?%";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal.add(new CageDTO(
                        rs.getInt("IdKaveza"),
                        rs.getString("Naziv"),
                        rs.getInt("Kapacitet"),
                        rs.getInt("Ukupno")
                ));
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - getByName", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public boolean insert(CageDTO cage){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO kavez " +
                       "VALUES (?, ?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cage.getId());
            ps.setString(2, cage.getName());
            ps.setInt(3, cage.getCapacity());
            ps.setInt(4, cage.getFullCapacity());

            retVal = ps.executeUpdate() == 1;
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean update(CageDTO cage){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE kavez SET " +
                       "Naziv=?, " +
                       "Kapacitet=?, " +
                       "Ukupno=? " +
                       "WHERE IdKaveza=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, cage.getName());
            ps.setInt(2, cage.getCapacity());
            ps.setInt(3, cage.getFullCapacity());
            ps.setInt(4, cage.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean delete(int cageID){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM kavez " +
                       "WHERE IdKaveza=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cageID);

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLCageDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return  retVal;
    }

}
