package data.dao.mysql;

import data.dao.AdministratorDAO;
import data.dto.AdministratorDTO;
import data.dto.EmploymentContractDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdministratorDAO implements AdministratorDAO {

    @Override
    public boolean insert(AdministratorDTO administrator, EmploymentContractDTO contract){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().insert(administrator, contract);
    }

    @Override
    public List<AdministratorDTO> adminstartors(){
        List<AdministratorDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z INNER JOIN administrator a ON z.JMBG = a.Zaposleni_JMBG";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new AdministratorDTO(
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

    @Override
    public AdministratorDTO getByUsername(String username){
        AdministratorDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                "z.BrojTelefona  FROM zaposleni z INNER JOIN administrator a ON z.JMBG = a.Zaposleni_JMBG " +
                "WHERE z.Username = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new AdministratorDTO(
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
    public boolean update(AdministratorDTO administrator){
        return  AzilUtilities.getDAOFactory().getEmployeeDAO().update(administrator);
    }

    @Override
    public boolean updateWithJMB(AdministratorDTO administrator, String oldJMB){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().updateWithJMB(administrator, oldJMB);
    }

    @Override
    public boolean delete(AdministratorDTO administrator){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM administrator "
                + "WHERE Zaposleni_JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, administrator.getJMB());

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

        retVal = AzilUtilities.getDAOFactory().getEmployeeDAO().delete(administrator);

        return retVal;
    }

    @Override
    public boolean exists(AdministratorDTO administrator){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM administrator WHERE Zaposleni_JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, administrator.getJMB());
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
                       "JOIN administrator a " +
                       "ON z.JMBG = a.Zaposleni_JMBG " +
                       "WHERE z.username = ? AND z.password = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, AzilUtilities.getSHA256(password));
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
    public AdministratorDTO login(String username, String password){
        AdministratorDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                       "z.BrojTelefona  FROM zaposleni z INNER JOIN administrator a ON z.JMBG = a.Zaposleni_JMBG " +
                       "WHERE z.Username = ? AND Password = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, AzilUtilities.getSHA256(password));
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new AdministratorDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

}
