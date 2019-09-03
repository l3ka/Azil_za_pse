package data.dao.mysql;

import data.dao.DogDAO;
import data.dto.DogDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDogDAO implements DogDAO {

    @Override
    public List<DogDTO> dogs(){
        List<DogDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas";
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
                        rs.getString("Fotografija")
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
    public DogDTO getByID(int ID){
        DogDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas WHERE IdPsa=?";
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
                        rs.getString("Fotografija")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        String query = "INSERT INTO pas (Ime, Pol, Rasa, DatumRodjenja, Visina, Tezina, Fotografija) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, dogDTO.getName());
            ps.setString(2, dogDTO.getGender());
            ps.setString(3, dogDTO.getBreed());
            ps.setDate(4, dogDTO.getDateOfBirth());
            ps.setInt(5, dogDTO.getHeight());
            ps.setDouble(6, dogDTO.getWeight());
            ps.setString(7, dogDTO.getImage());

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
                "Fotografija=? "
                + "WHERE IdPsa=? ";
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

            ps.setInt(8, dogDTO.getDogId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

        String query = "DELETE FROM pas "
                + "WHERE IdPsa=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dog.getDogId());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return  retVal;
    }
}
