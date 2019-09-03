package data.dao.mysql;

import data.dao.MedicineDAO;
import data.dto.MedicineDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLMedicineDAO implements MedicineDAO{

    public boolean insert(MedicineDTO medicine){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO lijek (NazivLijeka, Opis) "
                + "VALUES (?, ?) ";
        try{
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getDescription());

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

    public MedicineDTO getById(int id){
        MedicineDTO retVal = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM lijek WHERE IdLijeka=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                retVal = new MedicineDTO(
                        rs.getInt("IdLijeka"),
                        rs.getString("NazivLijeka"),
                        rs.getString("Opis")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean update(MedicineDTO medicine){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE lijek SET " +
                "NazivLijeka=?, " +
                "Opis=? " +
                "WHERE  IdLijeka=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getDescription());

            ps.setInt(3, medicine.getMedicineId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean delete(int Id){
        boolean retVal = true;

        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM lijek "
                + "WHERE IdLijeka=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, Id);

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
