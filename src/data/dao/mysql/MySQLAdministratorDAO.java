package data.dao.mysql;

import data.dao.AdministratorDAO;
import data.dto.AdministratorDTO;
import data.dto.EmploymentContractDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<AdministratorDTO> adminstartors(){
        List<AdministratorDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona " +
                "FROM zaposleni z INNER JOIN administrator a ON z.JMBG = a.Zaposleni_JMBG";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new AdministratorDTO(
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

    @Override
    public AdministratorDTO getByUsername(String username){
        AdministratorDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.Username, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, " +
                "z.BrojTelefona  FROM zaposleni z INNER JOIN administrator a ON z.JMBG = a.Zaposleni_JMBG " +
                "WHERE z.Username = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new AdministratorDTO(
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
    public boolean update(AdministratorDTO administrator){
        return  AzilUtilities.getDAOFactory().getEmployeeDAO().update(administrator);
    }

    @Override
    public boolean updateWithJMB(AdministratorDTO administrator, String oldJMB){
        return AzilUtilities.getDAOFactory().getEmployeeDAO().updateWithJMB(administrator, oldJMB);
    }
}
