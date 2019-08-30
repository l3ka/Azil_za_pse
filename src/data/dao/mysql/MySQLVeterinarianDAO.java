package data.dao.mysql;

import data.dao.VeterinarianDAO;
import data.dto.EmploymentContractDTO;
import data.dto.VeterinarianDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLVeterinarianDAO implements VeterinarianDAO {

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

    @Override
    public List<VeterinarianDTO> veterinarians(){
        List<VeterinarianDTO> retVal =  new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z INNER JOIN veterinar v ON z.JMBG = v.Zaposleni_JMBG";

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
        } catch (SQLException e) {
            e.printStackTrace();
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
                "z.BrojTelefona  FROM zaposleni z INNER JOIN veterinar v ON z.JMBG = v.Zaposleni_JMBG " +
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
        } catch (SQLException e) {
            e.printStackTrace();
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
}
