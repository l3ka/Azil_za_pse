package data.dao.mysql;

import data.dao.VeterinarinaDAO;
import data.dto.EmploymentContractDTO;
import data.dto.VeterinarianDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MySQLVeterinarinaDAO implements VeterinarinaDAO {

    @Override
    public boolean insert(VeterinarianDTO veterinarian, EmploymentContractDTO contract){
        boolean retVal = true;
        boolean insertSuccess = AzilUtilities.getDAOFactory().getEmployeeDAO().insert(veterinarian, contract);

        if(insertSuccess){
            Connection conn = null;
            PreparedStatement ps = null;

            String query = "INSERT INTO veterinar VALUES "
                    + "(?) ";
            try{
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, veterinarian.getJMB());

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
