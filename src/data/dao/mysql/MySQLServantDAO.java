package data.dao.mysql;

import data.dao.ServantDAO;
import data.dto.EmploymentContractDTO;
import data.dto.ServantDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLServantDAO implements ServantDAO {

    @Override
    public boolean insert(ServantDTO servant, EmploymentContractDTO contract){
        boolean retVal = true;
        boolean insertSuccess = AzilUtilities.getDAOFactory().getEmployeeDAO().insert(servant, contract);

        if(insertSuccess && AzilUtilities.getDAOFactory().getServantDAO().exists(servant)){
            Connection conn = null;
            PreparedStatement ps = null;

            String query = "INSERT INTO sluzbenik VALUES "
                    + "(?) ";
            try{
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, servant.getJMB());

                retVal = ps.executeUpdate() == 1;
            }catch (Exception e){
                retVal = false;
                e.printStackTrace();
            }finally {
                ConnectionPool.getInstance().checkIn(conn);
                DBUtilities.getInstance().close(ps);
            }
        }
        return retVal;
    }

    @Override
    public List<ServantDTO> servants(){
        List<ServantDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z INNER JOIN sluzbenik s ON z.JMBG = s.Zaposleni_JMBG";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new ServantDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                ));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    public ServantDTO getByUsername(String username){
        ServantDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                "z.BrojTelefona  FROM zaposleni z INNER JOIN sluzbenik s ON z.JMBG = s.Zaposleni_JMBG " +
                "WHERE z.Username = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new ServantDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
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
    public boolean update(ServantDTO servant){
        return  AzilUtilities.getDAOFactory().getEmployeeDAO().update(servant);
    }

    @Override
    public boolean updateWithJMB(ServantDTO servant, String oldJMB){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().updateWithJMB(servant, oldJMB);
    }

    @Override
    public boolean delete(ServantDTO servant){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM sluzbenik "
                + "WHERE Zaposleni_JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, servant.getJMB());

            retVal = ps.executeUpdate() == 1;
            if(!retVal){
                return retVal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        retVal = AzilUtilities.getDAOFactory().getEmployeeDAO().delete(servant);

        return retVal;
    }

    @Override
    public boolean exists(ServantDTO servant){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM sluzbenik WHERE Zaposleni_JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, servant.getJMB());
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException e) {
            retVal = false;
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return  retVal;
    }

    @Override
    public boolean exists(String username, String password) {
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM zaposleni z " +
                       "JOIN sluzbenik s " +
                       "ON z.JMBG = s.Zaposleni_JMBG " +
                       "WHERE z.username = ? AND z.password = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException e) {
            retVal = false;
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return  retVal;
    }

    @Override
    public ServantDTO login(String username, String password){
        ServantDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                "z.BrojTelefona  FROM zaposleni z INNER JOIN sluzbenik a ON z.JMBG = a.Zaposleni_JMBG " +
                "WHERE z.Username = ? AND Password = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, AzilUtilities.getSHA256(password));
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new ServantDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }
}
