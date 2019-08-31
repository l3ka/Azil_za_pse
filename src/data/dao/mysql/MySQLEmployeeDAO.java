package data.dao.mysql;

import data.dao.EmployeeDAO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import org.jetbrains.annotations.NotNull;
import util.AzilUtilities;

import java.sql.*;
import java.util.List;

public class MySQLEmployeeDAO implements EmployeeDAO {

    public boolean insert(EmployeeDTO employee, EmploymentContractDTO contract){
        boolean retVal = true;

        if(!AzilUtilities.getDAOFactory().getEmployeeDAO().exists(employee)){
            Connection conn = null;
            PreparedStatement ps = null;

            String query = "INSERT INTO zaposleni VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?) ";
            try{
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getJMB());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getSurname());
                ps.setString(4,employee.getUsername());
                ps.setString(5,employee.getPassword());
                ps.setString(6, employee.getQualifications());
                ps.setString(7, employee.getResidenceAddress());
                ps.setString(8, employee.getTelephoneNumber());

                retVal = ps.executeUpdate() == 1;
                if(!retVal) {
                    return retVal;
                }
            }catch (Exception e){
                retVal = false;
                e.printStackTrace();
            }finally {
                ConnectionPool.getInstance().checkIn(conn);
                DBUtilities.getInstance().close(ps);
            }
        }

        retVal = AzilUtilities.getDAOFactory().getContractDAO().insert(contract, employee);

        return  retVal;
    }

    public boolean update(@NotNull EmployeeDTO employee){
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

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean updateWithJMB(@NotNull EmployeeDTO employee, String oldJMB){
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
                "BrojTelefona=? "
                + "WHERE JMBG=? ";

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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    public boolean delete(EmployeeDTO employee){
        boolean retVal = true;

        List<EmploymentContractDTO> contracts = AzilUtilities.getDAOFactory().getContractDAO().contractsForEmployee(employee);
        for(EmploymentContractDTO contract : contracts){
            AzilUtilities.getDAOFactory().getContractDAO().delete(contract);
        }

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM zaposleni "
                + "WHERE JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            retVal = false;
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean exists(@NotNull EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM zaposleni WHERE JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getJMB());
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
        } catch (SQLException e) {
            retVal = false;
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return  retVal;
    }

}
