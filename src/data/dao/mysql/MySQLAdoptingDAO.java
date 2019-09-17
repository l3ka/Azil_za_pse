package data.dao.mysql;

import data.dao.AdoptingDAO;
import data.dto.AdoptingDTO;
import data.dto.FosterParentDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLAdoptingDAO implements AdoptingDAO {

    @Override
    public boolean insert(FosterParentDTO fosterParent, AdoptingDTO adopting){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO udomljavanjepsa " +
                       "VALUES (?, ?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, adopting.getDateFrom());
            ps.setInt(2, adopting.getDog().getDogId());
            ps.setString(3, fosterParent.getJMB());
            ps.setDate(4, adopting.getDateTo());

            retVal = ps.executeUpdate() == 1;
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public List<AdoptingDTO> getAdoptingForFosterParent(FosterParentDTO fosterParent){
        List<AdoptingDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM udomljavanjepsa WHERE UdomiteljJMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, fosterParent.getJMB());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new AdoptingDTO(
                        AzilUtilities.getDAOFactory().getDogDAO().getByID(rs.getInt("IdPsa")),
                        rs.getDate("datumOd"),
                        rs.getDate("DatumDo")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDAO - getAdoptingForFosterParent", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public boolean update(FosterParentDTO fosterParentDTO, AdoptingDTO adopting, Date dateFrom, int dogId, String JMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE udomljavanjepsa SET " +
                       "DatumOd=?, " +
                       "IdPsa=?, " +
                       "UdomiteljJMBG=?, " +
                       "DatumDo=? " +
                       "WHERE DatumOd=? AND IdPsa=? AND UdomiteljJMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, adopting.getDateFrom());
            ps.setInt(2, adopting.getDog().getDogId());
            ps.setString(3, fosterParentDTO.getJMB());
            ps.setDate(4, adopting.getDateTo());

            ps.setDate(5, dateFrom);
            ps.setInt(6, dogId);
            ps.setString(7, JMB);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean delete(Date dateFrom, int dogId, String JMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM udomljavanjepsa " +
                       "WHERE DatumOd=? AND IdPsa=? AND UdomiteljJMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, dateFrom);
            ps.setInt(2, dogId);
            ps.setString(3, JMB);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

}
