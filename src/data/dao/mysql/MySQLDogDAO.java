package data.dao.mysql;

import data.dao.DogDAO;
import data.dto.DogDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLDogDAO implements DogDAO {

    @Override
    public DogDTO getLastDog() {
        DogDTO retVal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas WHERE IdPsa=(SELECT max(IdPsa) FROM pas) AND Obrisan = 0";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new DogDTO(
                        rs.getInt("IdPsa"),
                        rs.getString("Pol"),
                        rs.getString("Ime"),
                        rs.getString("Rasa"),
                        rs.getInt("Visina"),
                        rs.getDouble("Tezina"),
                        rs.getDate("DatumRodjenja"),
                        rs.getString("Fotografija"),
                        rs.getBoolean("Udomljen")
                );
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - getLastDog", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<DogDTO> dogs(){
        List<DogDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas WHERE Obrisan = 0";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new DogDTO(
                        rs.getInt("IdPsa"),
                        rs.getString("Pol"),
                        rs.getString("Ime"),
                        rs.getString("Rasa"),
                        rs.getInt("Visina"),
                        rs.getDouble("Tezina"),
                        rs.getDate("DatumRodjenja"),
                        rs.getString("Fotografija"),
                        rs.getBoolean("Udomljen")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - dogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public DogDTO getByID(int ID){
        DogDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas WHERE IdPsa=? AND Obrisan = 0";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ID);
            rs = ps.executeQuery();

            if(rs.next()) {
                retVal = new DogDTO(
                        rs.getInt("IdPsa"),
                        rs.getString("Pol"),
                        rs.getString("Ime"),
                        rs.getString("Rasa"),
                        rs.getInt("Visina"),
                        rs.getDouble("Tezina"),
                        rs.getDate("DatumRodjenja"),
                        rs.getString("Fotografija"),
                        rs.getBoolean("Udomljen")
                );
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - getByID", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<DogDTO> getAdoptedDogs(LocalDate dateFrom) {
        List<DogDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas p " +
                       "LEFT OUTER JOIN udomljavanjepsa up ON up.IdPsa=p.IdPsa " +
                       "WHERE p.Udomljen=1 AND up.DatumOd>=DATE(?) AND Obrisan = 0";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, dateFrom.toString());
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new DogDTO(
                        rs.getInt("IdPsa"),
                        rs.getString("Pol"),
                        rs.getString("Ime"),
                        rs.getString("Rasa"),
                        rs.getInt("Visina"),
                        rs.getDouble("Tezina"),
                        rs.getDate("DatumRodjenja"),
                        rs.getString("Fotografija"),
                        rs.getBoolean("Udomljen")
                ));
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - getAdoptedDogs", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public List<DogDTO> dogsByBreed(String breed) {
        List<DogDTO> retVal = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas " +
                       "WHERE Rasa=? AND Obrisan = 0";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, breed);
            rs = ps.executeQuery();

            while(rs.next()) {
                retVal.add(new DogDTO(
                        rs.getInt("IdPsa"),
                        rs.getString("Pol"),
                        rs.getString("Ime"),
                        rs.getString("Rasa"),
                        rs.getInt("Visina"),
                        rs.getDouble("Tezina"),
                        rs.getDate("DatumRodjenja"),
                        rs.getString("Fotografija"),
                        rs.getBoolean("Udomljen")
                ));
            }
        } catch(SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - dogsByBreed", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    @Override
    public boolean insert(DogDTO dogDTO){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO pas (Ime, Pol, Rasa, DatumRodjenja, Visina, Tezina, Fotografija, Udomljen, Obrisan) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, dogDTO.getName());
            ps.setString(2, dogDTO.getGender());
            ps.setString(3, dogDTO.getBreed());
            ps.setDate(4, dogDTO.getDateOfBirth());
            ps.setInt(5, dogDTO.getHeight());
            ps.setDouble(6, dogDTO.getWeight());
            ps.setString(7, dogDTO.getImage());
            ps.setBoolean(8, dogDTO.isAdopted());
            ps.setInt(9, 0);

            retVal = ps.executeUpdate() == 1;
        } catch (Exception ex) {
            retVal = false;
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - insert", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public boolean update(DogDTO dogDTO){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE pas SET " +
                        "Pol=?," +
                        "Ime=?, " +
                        "Rasa=?, " +
                        "Visina=?, " +
                        "Tezina=?, " +
                        "DatumRodjenja=?, " +
                        "Fotografija=?, " +
                        "Udomljen=? " +
                        "WHERE IdPsa=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, dogDTO.getGender());
            ps.setString(2, dogDTO.getName());
            ps.setString(3, dogDTO.getBreed());
            ps.setInt(4, dogDTO.getHeight());
            ps.setDouble(5, dogDTO.getWeight());
            ps.setDate(6, dogDTO.getDateOfBirth());
            ps.setString(7, dogDTO.getImage());
            ps.setBoolean(8, dogDTO.isAdopted());
            ps.setInt(9, dogDTO.getDogId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    @Override
    public boolean delete(DogDTO dog){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE pas " +
                       "SET Obrisan = 1 " +
                       "WHERE IdPsa=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dog.getDogId());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLDogDAO - delete", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return  retVal;
    }

}
