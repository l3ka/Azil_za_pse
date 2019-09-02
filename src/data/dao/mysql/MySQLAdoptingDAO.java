package data.dao.mysql;

import data.dao.AdoptingDAO;
import data.dto.AdoptingDTO;
import data.dto.DogDTO;
import data.dto.FosterParentDTO;
import util.AzilUtilities;

import java.lang.annotation.Inherited;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdoptingDAO implements AdoptingDAO {

    @Override
    public boolean insert(FosterParentDTO fosterParent, AdoptingDTO adopting){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO udomljavanjepsa "
                + "VALUES (?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, adopting.getDateFrom());
            ps.setInt(2, adopting.getDog().getDogId());
            ps.setString(3, fosterParent.getJMB());
            ps.setDate(4, adopting.getDateTo());

            retVal = ps.executeUpdate() == 1;
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public List<AdoptingDTO> getAdoptingForFosterParent(FosterParentDTO fosterParent){
        List<AdoptingDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM udomljavanjepsa WHERE Udomitelj_JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, fosterParent.getJMB());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new AdoptingDTO(
                        AzilUtilities.getDAOFactory().getDogDAO().getByID(rs.getInt("Pas_IdPsa")),
                        rs.getDate("datumOd"),
                        rs.getDate("DatumDo")
                ));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public boolean update(FosterParentDTO fosterParentDTO, AdoptingDTO adopting, Date Od, int IdPsa, String JMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE udomljavanjepsa SET " +
                "DatumOd=?, " +
                "Pas_IdPsa=?, " +
                "Udomitelj_JMBG=?, " +
                "DatumDo=? " +
                "WHERE DatumOd=? AND Pas_IdPsa=? AND Udomitelj_JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, adopting.getDateFrom());
            ps.setInt(2, adopting.getDog().getDogId());
            ps.setString(3, fosterParentDTO.getJMB());
            ps.setDate(4, adopting.getDateTo());

            ps.setDate(5, Od);
            ps.setInt(6, IdPsa);
            ps.setString(7, JMB);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }
}
