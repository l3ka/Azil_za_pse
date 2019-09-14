package data.dao.mysql;

import data.dao.ContractDAO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLContractDAO implements ContractDAO {

    public boolean insert(EmploymentContractDTO contract, EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO ugovororadu (Pozicija, Aktivan, Plata) " +
                       "VALUES (?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contract.getPosition());
            ps.setInt(2, contract.getActive());
            ps.setDouble(3, contract.getSalary());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                contract.setEmploymentContractId(generatedKeys.getInt(1));
            } else {
                return false;
            }

            retVal = joinContractToEmployee(contract, employee);
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    private boolean joinContractToEmployee(EmploymentContractDTO contract, EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO zaposleni_ugovor " +
                       "VALUES (?, ?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, contract.getSigningDate());
            ps.setString(2, employee.getJMB());
            ps.setInt(3, contract.getEmploymentContractId());
            ps.setDate(4, contract.getValidationDate());

            retVal = ps.executeUpdate() == 1;

        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - joinContractToEmployee", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public List<EmploymentContractDTO> contractsForEmployee(EmployeeDTO employee){
        List<EmploymentContractDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT u.IdUgovora, u.Aktivan, u.Pozicija, zu.Od, zu.Do, u.Plata FROM zaposleni z " +
                       "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                       "INNER JOIN ugovororadu  u ON u.IdUgovora = zu.IdUgovora " +
                       "WHERE z.JMBG = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());

            rs = ps.executeQuery();
            while (rs.next()){
                retVal.add(new EmploymentContractDTO(
                        rs.getInt("IdUgovora"),
                        rs.getInt("Aktivan"),
                        rs.getString("Pozicija"),
                        rs.getDate("Od"),
                        rs.getDate("Do"),
                        rs.getDouble("Plata")
                ));
            }
        } catch (Exception ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - contractsForEmployee", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public EmploymentContractDTO contractById(int contractId){
        EmploymentContractDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT u.IdUgovora, u.Aktivan, u.Pozicija, zu.Od, zu.Do, u.Plata FROM zaposleni z " +
                       "INNER JOIN zaposleni_ugovor zu ON z.JMBG = zu.ZaposlenikJMBG " +
                       "INNER JOIN ugovororadu  u ON u.IdUgovora = zu.IdUgovora " +
                       "WHERE u.IdUgovora = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, contractId);

            rs = ps.executeQuery();
            while (rs.next()){
                retVal = new EmploymentContractDTO(
                        rs.getInt("IdUgovora"),
                        rs.getInt("Aktivan"),
                        rs.getString("Pozicija"),
                        rs.getDate("Od"),
                        rs.getDate("Do"),
                        rs.getDouble("Plata")
                );
            }
        } catch (Exception ex){
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - contractById", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean delete(EmploymentContractDTO contract){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM ugovororadu " +
                       "WHERE IdUgovora = ? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, contract.getEmploymentContractId());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean update(EmploymentContractDTO contract){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query1 = "UPDATE ugovororadu SET " +
                        "Pozicija=?," +
                        "Aktivan=?, " +
                        "Plata=? " +
                        "WHERE IdUgovora=? ";
        String query2 = "UPDATE zaposleni_ugovor SET " +
                        "Do=? " +
                        "WHERE IdUgovora=? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query1);
            ps.setString(1, contract.getPosition());
            ps.setInt(2, contract.getActive());
            ps.setDouble(3, contract.getSalary());
            ps.setInt(4, contract.getEmploymentContractId());

            ps.executeUpdate();

            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query2);
            ps.setDate(1, contract.getValidationDate());
            ps.setInt(2, contract.getEmploymentContractId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLContractDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            retVal = false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

}
