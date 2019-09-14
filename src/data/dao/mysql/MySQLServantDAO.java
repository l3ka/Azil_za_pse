package data.dao.mysql;

import data.dao.ServantDAO;
import data.dto.EmploymentContractDTO;
import data.dto.LoggerDTO;
import data.dto.ServantDTO;
import util.AzilUtilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLServantDAO implements ServantDAO {

    @Override
    public boolean insert(ServantDTO servant, EmploymentContractDTO contract){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().insert(servant, contract);
    }

    @Override
    public List<ServantDTO> servants(){
        List<ServantDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                       "FROM zaposleni z " + "" +
                       "INNER JOIN sluzbenik s ON z.JMBG = s.SluzbenikJMBG " +
                       "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                       "INNER JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                       "WHERE Aktivan = 1";
        ;

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
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - servants", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

    @Override
    public List<ServantDTO> servantsDeactivated(){
        List<ServantDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z " + "" +
                "INNER JOIN sluzbenik s ON z.JMBG = s.SluzbenikJMBG " +
                "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                "INNER JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                "WHERE Aktivan = 0";
        ;

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
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - servants", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
                       "z.BrojTelefona  FROM zaposleni z INNER JOIN sluzbenik s ON z.JMBG = s.SluzbenikJMBG " +
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
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - getByUsername", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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

        String query = "DELETE FROM sluzbenik " +
                       "WHERE SluzbenikJMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, servant.getJMB());

            retVal = ps.executeUpdate() == 1;
            if(!retVal){
                return retVal;
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
        String query = "SELECT * FROM sluzbenik WHERE SluzbenikJMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, servant.getJMB());
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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
                       "ON z.JMBG = s.SluzbenikJMBG " +
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
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
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

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                       "FROM zaposleni z " + "" +
                       "INNER JOIN sluzbenik s ON z.JMBG = s.SluzbenikJMBG " +
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
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLServantDAO - login", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return retVal;
    }

}
