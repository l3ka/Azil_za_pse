package data.dao.mysql;

import data.dao.MedicalResultDAO;
import data.dto.DogDTO;
import data.dto.LoggerDTO;
import data.dto.MedicalResultDTO;
import data.dto.VeterinarianDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLMedicalResultDAO implements MedicalResultDAO {

    @Override
    public boolean insert(MedicalResultDTO medicalResult){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO nalaz (DatumPregleda, Dijagnoza, Veterinar_Zaposleni_JMBG, Pas_IdPsa) "
                + "VALUES (?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, medicalResult.getDate());
            ps.setString(2, medicalResult.getResultsAndOpinion());
            ps.setString(3, medicalResult.getVeterinarianJMB());
            ps.setInt(4, medicalResult.getDogId());

            retVal = ps.executeUpdate() == 1;
        }catch (Exception ex){
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        }finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public List<MedicalResultDTO> medicalResults(VeterinarianDTO veterinarian){
        List<MedicalResultDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM nalaz WHERE  Veterinar_Zaposleni_JMBG=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, veterinarian.getJMB());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new MedicalResultDTO(
                        rs.getInt("IdNalaza"),
                        rs.getString("Dijagnoza"),
                        rs.getDate("DatumPregleda"),
                        rs.getInt("Pas_IdPsa"),
                        rs.getString("Veterinar_Zaposleni_JMBG")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<MedicalResultDTO> medicalResults(DogDTO dog){
        List<MedicalResultDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM nalaz WHERE  Pas_IdPsa=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dog.getDogId());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new MedicalResultDTO(
                        rs.getInt("IdNalaza"),
                        rs.getString("Dijagnoza"),
                        rs.getDate("DatumPregleda"),
                        rs.getInt("Pas_IdPsa"),
                        rs.getString("Veterinar_Zaposleni_JMBG")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<MedicalResultDTO> medicalResults(DogDTO dog, VeterinarianDTO veterinarian){
        List<MedicalResultDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM nalaz WHERE  Pas_IdPsa=? AND Veterinar_Zaposleni_JMBG=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dog.getDogId());
            ps.setString(2, veterinarian.getJMB());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new MedicalResultDTO(
                        rs.getInt("IdNalaza"),
                        rs.getString("Dijagnoza"),
                        rs.getDate("DatumPregleda"),
                        rs.getInt("Pas_IdPsa"),
                        rs.getString("Veterinar_Zaposleni_JMBG")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public MedicalResultDTO getById(int Id){
        MedicalResultDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM nalaz WHERE  IdNalaza=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Id);
            rs = ps.executeQuery();

            if (rs.next()){
                retVal = new MedicalResultDTO(
                        rs.getInt("IdNalaza"),
                        rs.getString("Dijagnoza"),
                        rs.getDate("DatumPregleda"),
                        rs.getInt("Pas_IdPsa"),
                        rs.getString("Veterinar_Zaposleni_JMBG")
                );
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public boolean update(MedicalResultDTO medicalResult){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE nalaz SET " +
                "DatumPregleda=?, " +
                "Dijagnoza=? " +
                "WHERE  Pas_IdPsa=? AND Veterinar_Zaposleni_JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, medicalResult.getDate());
            ps.setString(2, medicalResult.getResultsAndOpinion());

            ps.setInt(3, medicalResult.getDogId());
            ps.setString(4, medicalResult.getVeterinarianJMB());

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public boolean update(MedicalResultDTO medicalResult, int dogId, String veterinarianJMB){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE nalaz SET " +
                "DatumPregleda=?, " +
                "Dijagnoza=?, " +
                "Veterinar_Zaposleni_JMBG=?, " +
                "Pas_IdPsa=? " +
                "WHERE  Pas_IdPsa=? AND Veterinar_Zaposleni_JMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, medicalResult.getDate());
            ps.setString(2, medicalResult.getResultsAndOpinion());
            ps.setString(3, medicalResult.getVeterinarianJMB());
            ps.setInt(4, medicalResult.getDogId());

            ps.setInt(5, dogId);
            ps.setString(6, veterinarianJMB);

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public boolean delete(int Id){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM nalaz "
                + "WHERE IdNalaza=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Id);

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLMedicalResultDAO", ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return  retVal;
    }

}
