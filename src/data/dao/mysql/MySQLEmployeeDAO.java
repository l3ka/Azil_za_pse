package data.dao.mysql;

import data.dao.EmployeeDAO;
import data.dto.*;
import util.AzilUtilities;
import java.sql.*;
import java.util.Calendar;

public class MySQLEmployeeDAO implements EmployeeDAO {

    @Override
    public EmployeeDTO getEmployeeByJMB(String jmb) {
        EmployeeDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.UserName, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona, " +
                       "zu.Od, zu.ZaposlenikJMBG, zu.IdUgovora IdUgovorazZU, zu.Do, " +
                       "uor.IdUgovora IdUgovoraUOR, uor.Pozicija, uor.Aktivan, uor.Plata " +
                       "FROM zaposleni_ugovor zu " +
                       "JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                       "JOIN zaposleni z ON zu.ZaposlenikJMBG = z.JMBG " +
                       "WHERE z.JMBG = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, jmb);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("Pozicija").equals("Administrtor")) {
                    retVal = new AdministratorDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
                else if (rs.getString("Pozicija").equals("Veterinar")) {
                    retVal = new VeterinarianDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
                else if (rs.getString("Pozicija").equals("Sluzbenik")) {
                    retVal = new ServantDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - getEmployeeByJMB", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean insert(EmployeeDTO employee, EmploymentContractDTO contract){
        boolean retVal = true;

        if(!AzilUtilities.getDAOFactory().getEmployeeDAO().exists(employee)) {
            Connection conn = null;
            CallableStatement cs = null;

            String query = "call add_employee_contract(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                conn = ConnectionPool.getInstance().checkOut();
                cs = conn.prepareCall(query);
                cs.setString(1, employee.getJMB());
                cs.setString(2, employee.getName());
                cs.setString(3, employee.getSurname());
                cs.setString(4, employee.getUsername());
                cs.setString(5, employee.getPassword());
                cs.setString(6, employee.getQualifications());
                cs.setString(7, employee.getResidenceAddress());
                cs.setString(8, employee.getTelephoneNumber());
                cs.setString(9, contract.getPosition());
                cs.setDouble(10, contract.getSalary());
                cs.setDate(11, contract.getSigningDate());
                cs.setDate(12, contract.getValidationDate());
                retVal = !cs.execute();
            } catch (Exception ex) {
                retVal = false;
                AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            } finally {
                ConnectionPool.getInstance().checkIn(conn);
                DBUtilities.getInstance().close(cs);
            }
        }
        return  retVal;
    }

    public boolean update(EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE zaposleni SET " +
                "Ime=?, " +
                "Prezime=?, " +
                "Username=?, " +
                "Password=?, " +
                "StrucnaSprema=?, " +
                "MjestoPrebivalista=?, " +
                "BrojTelefona=? "
                + "WHERE JMBG=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getUsername());
            ps.setString(4, employee.getPassword());
            ps.setString(5, employee.getQualifications());
            ps.setString(6, employee.getResidenceAddress());
            ps.setString(7, employee.getTelephoneNumber());

            ps.setString(8, employee.getJMB());

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean updateWithJMB(EmployeeDTO employee, String oldJMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE zaposleni SET " +
                        "JMBG=?," +
                        "Ime=?, " +
                        "Prezime=?, " +
                        "Username=?, " +
                        "Password=?, " +
                        "StrucnaSprema=?, " +
                        "MjestoPrebivalista=?, " +
                        "BrojTelefona=? " +
                       "WHERE JMBG=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1,employee.getJMB());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getSurname());
            ps.setString(4, employee.getUsername());
            ps.setString(5, employee.getPassword());
            ps.setString(6, employee.getQualifications());
            ps.setString(7, employee.getResidenceAddress());
            ps.setString(8, employee.getTelephoneNumber());

            ps.setString(9, oldJMB);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - updateWithJMB", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean delete(EmployeeDTO employee) {
        boolean retVal = true;

        /*
        List<EmploymentContractDTO> contracts = AzilUtilities.getDAOFactory().getContractDAO().contractsForEmployee(employee);
        for(EmploymentContractDTO contract : contracts){
            AzilUtilities.getDAOFactory().getContractDAO().delete(contract);
        }
         */

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE ugovororadu " +
                       "SET Aktivan = 0 " +
                       "WHERE IdUgovora = (SELECT IdUgovora FROM zaposleni_ugovor WHERE ZaposlenikJMBG = ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean activate(EmployeeDTO employee) {
        boolean retVal = true;

        /*
        List<EmploymentContractDTO> contracts = AzilUtilities.getDAOFactory().getContractDAO().contractsForEmployee(employee);
        for(EmploymentContractDTO contract : contracts){
            AzilUtilities.getDAOFactory().getContractDAO().delete(contract);
        }
         */

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE ugovororadu " +
                       "SET Aktivan = 1 " +
                       "WHERE IdUgovora = (SELECT IdUgovora FROM zaposleni_ugovor WHERE ZaposlenikJMBG = ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - activate", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean exists(EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM zaposleni WHERE JMBG = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());
            rs = ps.executeQuery();

           retVal = rs.next();
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }

        return  retVal;
    }

    @Override
    public boolean exists(String username, String JMB) {
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM zaposleni WHERE username = ? OR JMBG = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, JMB);
            rs = ps.executeQuery();

            retVal = rs.next();
        } catch (SQLException ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLEmployeeDAO - exists", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return  retVal;
    }

}
