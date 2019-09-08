package data.dao.mysql;

import data.dao.FosterParentDAO;
import data.dto.FosterParentDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLFosterParentDAO implements FosterParentDAO  {

    @Override
    public List<FosterParentDTO> fosterParents(){
        List<FosterParentDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM udomitelj";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new FosterParentDTO(
                        rs.getString("JMBG"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona")
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
    public List<FosterParentDTO> fosterParents(LocalDate dateFrom) {
        List<FosterParentDTO> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT DISTINCT * FROM udomitelj u " +
                       "LEFT OUTER JOIN udomljavanjepsa up ON u.JMBG=up.Udomitelj_JMBG " +
                       "WHERE up.Pas_IdPsa is not null AND DatumOd>=DATE(?) " +
                       "GROUP BY up.Udomitelj_JMBG ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, dateFrom.toString());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new FosterParentDTO(
                        rs.getString("JMBG"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona")
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
    public FosterParentDTO getByJMB(String JMB){
        FosterParentDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM udomitelj WHERE JMBG=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, JMB);
            rs = ps.executeQuery();

            while (rs.next())
                retVal = new FosterParentDTO(
                        rs.getString("JMBG"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public boolean insert(FosterParentDTO fosterParent){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO udomitelj "
                + "VALUES (?, ?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, fosterParent.getJMB());
            ps.setString(2, fosterParent.getName());
            ps.setString(3, fosterParent.getSurname());
            ps.setString(4, fosterParent.getResidenceAddress());
            ps.setString(5, fosterParent.getTelephoneNumber());

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
    public boolean update(FosterParentDTO fosterParent){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE udomitelj SET " +
                "Ime=?, " +
                "Prezime=?, " +
                "MjestoPrebivalista=?, " +
                "BrojTelefona=? " +
                "WHERE JMBG=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, fosterParent.getName());
            ps.setString(2, fosterParent.getSurname());
            ps.setString(3, fosterParent.getResidenceAddress());
            ps.setString(4, fosterParent.getTelephoneNumber());

            ps.setString(5, fosterParent.getJMB());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean updateWithJMB(FosterParentDTO fosterParent, String oldJMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE udomitelj SET " +
                "JMBG=?, " +
                "Ime=?, " +
                "Prezime=?, " +
                "MjestoPrebivalista=?, " +
                "BrojTelefona=? " +
                "WHERE JMBG=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, fosterParent.getJMB());
            ps.setString(2, fosterParent.getName());
            ps.setString(3, fosterParent.getSurname());
            ps.setString(4, fosterParent.getResidenceAddress());
            ps.setString(5, fosterParent.getTelephoneNumber());

            ps.setString(6, oldJMB);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean delete(String JMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM udomitelj "
                + "WHERE JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, JMB);

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return  retVal;
    }
}
