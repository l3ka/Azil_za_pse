package data.dao.mysql;

import data.dao.DogInCageDAO;
import data.dto.CageDTO;
import data.dto.DogInCageDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDogInCage implements DogInCageDAO {

    @Override
    public boolean insert(CageDTO cage, DogInCageDTO dogInCage){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO kavez_pas  "
                + "VALUES (?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, dogInCage.getDateForm());
            ps.setInt(2, cage.getId());
            ps.setInt(3, dogInCage.getDog().getDogId());
            ps.setDate(4, dogInCage.getDateTo());

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

    public boolean update(CageDTO cage, DogInCageDTO dogInCage, Date dateFrom, int cageId, int dogId){
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
            ps.setDate(1, dogInCage.getDateForm());
            ps.setInt(2, cage.getId());
            ps.setInt(3, dogInCage.getDog().getDogId());
            ps.setDate(4, dogInCage.getDateTo());

            ps.setDate(5, dateFrom);
            ps.setInt(6, cageId);
            ps.setInt(7, dogId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }
}
