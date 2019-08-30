package data.dao.mysql;

import data.dao.ContractDAO;
import data.dto.EmployeeDTO;
import data.dto.EmploymentContractDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLContractDAO implements ContractDAO {
    public boolean insert(EmploymentContractDTO contract, EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO ugovororadu (Pozicija, aktivan, Plata) VALUES "
                + "(?, ?, ?) ";
        try{
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
        }catch (Exception e){
            retVal = false;
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    private boolean joinContractToEmployee(EmploymentContractDTO contract, EmployeeDTO employee){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO zaposleni_ugovor VALUES "
                + "(?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, contract.getSigningDate());
            ps.setString(2, employee.getJMB());
            ps.setInt(3, contract.getEmploymentContractId());
            ps.setDate(4, contract.getValidationDate());

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
}
