package data.dao.mysql;

import data.dao.ServantDAO;
import data.dto.EmploymentContractDTO;
import data.dto.ServantDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MySQLServantDAO implements ServantDAO {

    @Override
    public boolean insert(ServantDTO servant, EmploymentContractDTO contract){
        boolean retVal = true;
        boolean insertSuccess = AzilUtilities.getDAOFactory().getEmployeeDAO().insert(servant, contract);

        if(insertSuccess){
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
}
