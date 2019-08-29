package data.dao.mysql;

import data.dao.AdministratorDAO;
import data.dto.AdministratorDTO;
import data.dto.EmploymentContractDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MySQLAdministratorDAO implements AdministratorDAO {

    @Override
    public boolean insert(AdministratorDTO administrator, EmploymentContractDTO contract){
        boolean retVal = true;
        boolean insertSuccess = AzilUtilities.getDAOFactory().getEmployeeDAO().insert(administrator, contract);

        if(insertSuccess){
            Connection conn = null;
            PreparedStatement ps = null;

            String query = "INSERT INTO administrator VALUES "
                    + "(?) ";
            try{
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, administrator.getJMB());

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
}
