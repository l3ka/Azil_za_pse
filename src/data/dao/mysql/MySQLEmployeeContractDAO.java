package data.dao.mysql;

import data.dao.EmployeeContractDAO;
import data.dto.*;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLEmployeeContractDAO implements EmployeeContractDAO {

    @Override
    public List<EmployeeContractDTO> getAllEmployeeContract() {
        ArrayList<EmployeeContractDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT z.JMBG, z.Ime, z.Prezime, z.UserName, z.Password, z.StrucnaSprema, z.MjestoPrebivalista, z.BrojTelefona, " +
                       "zu.Od, zu.ZaposlenikJMBG, zu.IdUgovora IdUgovorazZU, zu.Do, " +
                       "uor.IdUgovora IdUgovoraUOR, uor.Pozicija, uor.Aktivan, uor.Plata " +
                       "FROM zaposleni_ugovor zu " +
                       "JOIN ugovororadu uor ON zu.IdUgovora = uor.IdUgovora " +
                       "JOIN zaposleni z ON zu.ZaposlenikJMBG = z.JMBG";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                /*
                EmployeeDTO employee = null;
                if (rs.getString("Pozicija").equals("Administrtor")) {
                    employee = new AdministratorDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
                else if (rs.getString("Pozicija").equals("Veterinar")) {
                    employee = new VeterinarianDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
                else if (rs.getString("Pozicija").equals("Sluzbenik")) {
                    employee = new ServantDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("StrucnaSprema"), rs.getString("MjestoPrebivalista"), rs.getString("BrojTelefona"), rs.getString("JMBG"));
                }
                EmploymentContractDTO employmentContract = new EmploymentContractDTO(rs.getInt("IdUgovoraUOR"), rs.getInt("Aktivan"), rs.getString("Pozicija"), rs.getDate("Od"), rs.getDate("Do"), rs.getDouble("Plata"));
                 */
                retVal.add(new EmployeeContractDTO(rs.getDate("Od"), rs.getString("ZaposlenikJMBG"), rs.getInt("IdUgovorazZU"), rs.getDate("Do")));
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDogDao - getAllEmployeeContract", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public boolean update(EmployeeContractDTO employeeContract) {
        boolean retVal = false;

        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE zaposleni_ugovor SET Do = ? " +
                       "WHERE Od = ? AND ZaposlenikJMBG = ? AND IdUgovora = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, employeeContract.getTo());
            ps.setDate(2, employeeContract.getFrom());
            ps.setString(3, employeeContract.getJmbEmployee());
            ps.setInt(4, employeeContract.getIdEmploymentContract());
            retVal = !ps.execute();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDogDao - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
            retVal = false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }


}
