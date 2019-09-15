package data.dao.mysql;

import data.dao.VeterinarianDAO;
import data.dto.EmploymentContractDTO;
import data.dto.LoggerDTO;
import data.dto.VeterinarianDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLVeterinarianDAO implements VeterinarianDAO {

    @Override
    public boolean insert(VeterinarianDTO veterinarian, EmploymentContractDTO contract){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().insert(veterinarian, contract);
    }

    @Override
    public List<VeterinarianDTO> veterinarians(){
        List<VeterinarianDTO> retVal =  new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                       "FROM zaposleni z " +
                       "INNER JOIN veterinar v ON z.JMBG = v.VeterinarJMBG " +
                       "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                       "INNER JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                       "WHERE Aktivan = 1";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new VeterinarianDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - veterinarians", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public List<VeterinarianDTO> veterinariansDeactivated(){
        List<VeterinarianDTO> retVal =  new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z " +
                "INNER JOIN veterinar v ON z.JMBG = v.VeterinarJMBG " +
                "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                "INNER JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                "WHERE Aktivan = 0";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new VeterinarianDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - veterinarians", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    public VeterinarianDTO getByUsername(String username){
        VeterinarianDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                       "z.BrojTelefona  FROM zaposleni z INNER JOIN veterinar v ON z.JMBG = v.VeterinarJMBG " +
                       "WHERE z.Username = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new VeterinarianDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                );
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - getByUsername", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public boolean update(VeterinarianDTO veterinarian){
        return  AzilUtilities.getDAOFactory().getEmployeeDAO().update(veterinarian);
    }

    @Override
    public  boolean updateWithJMB(VeterinarianDTO veterinarian, String oldJMB){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().updateWithJMB(veterinarian, oldJMB);
    }

    @Override
    public boolean delete(VeterinarianDTO veterinarian){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM veterinar " +
                       "WHERE VeterinarJMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, veterinarian.getJMB());

            retVal = ps.executeUpdate() == 1;
            if(!retVal){
                return retVal;
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        retVal = AzilUtilities.getDAOFactory().getEmployeeDAO().delete(veterinarian);

        return retVal;
    }

    @Override
    public boolean exists(VeterinarianDTO veterinarian){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM veterinar WHERE VeterinarJMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, veterinarian.getJMB());
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
                       "JOIN veterinar v " +
                       "ON z.JMBG = v.VeterinarJMBG " +
                       "WHERE z.username = ? AND z.password = ?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, AzilUtilities.getSHA256(password));
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return  retVal;
    }

    @Override
    public VeterinarianDTO login(String username, String password){
        VeterinarianDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                       "FROM zaposleni z " +
                       "INNER JOIN veterinar v ON z.JMBG = v.VeterinarJMBG " +
                       "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                       "INNER JOIN ugovororadu uor ON zu.IdUgovora= uor.IdUgovora " +
                       "WHERE z.Username = ? AND Password = ? AND Aktivan = 1";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, AzilUtilities.getSHA256(password));
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new VeterinarianDTO(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("StrucnaSprema"),
                        rs.getString("MjestoPrebivalista"),
                        rs.getString("BrojTelefona"),
                        rs.getString("JMBG")
                );
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLVeterinarianDAO - login", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

}
