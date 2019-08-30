package data.dao.mysql;

import data.dao.EmployeeDAO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;
import util.AzilUtilities;

import java.sql.*;

public class MySQLEmployeeDAO implements EmployeeDAO {

    public boolean insert(EmployeeDTO employee, EmploymentContractDTO contract){
        boolean retVal = true;
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
            if(!retVal){
                return retVal;
            }

            retVal = AzilUtilities.getDAOFactory().getContractDAO().insert(contract, employee);
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
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

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
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

            retVal = ps.executeUpdate() == 1;
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


        return retVal;
    }
}
