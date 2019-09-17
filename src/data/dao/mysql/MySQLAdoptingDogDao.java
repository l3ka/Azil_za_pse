package data.dao.mysql;

import data.dao.AdoptingDogDAO;
import data.dto.AdoptingDogDTO;
import data.dto.LoggerDTO;
import util.AzilUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLAdoptingDogDao implements AdoptingDogDAO {

    @Override
    public boolean update(AdoptingDogDTO adoptingDog) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE udomljavanjepsa SET " +
                       "DatumOd=?, " +
                       "IdPsa=?, " +
                       "UdomiteljJMBG=?, " +
                       "DatumDo=? " +
                       "WHERE IdPsa=? AND UdomiteljJMBG=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, adoptingDog.getDateFrom());
            ps.setInt(2, adoptingDog.getIdDog());
            ps.setString(3, adoptingDog.getJmbFosterParent());
            ps.setDate(4,adoptingDog.getDateTo());
            ps.setInt(5, adoptingDog.getIdDog());
            ps.setString(6, adoptingDog.getJmbFosterParent());

            retVal = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDogDao - update", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return retVal;
    }

    @Override
    public List<AdoptingDogDTO> getAllAdoptings() {
        ArrayList<AdoptingDogDTO> retVal = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT p.IdPsa, p.Ime ImePsa, p.Pol, p.Rasa, p.DatumRodjenja, p.Visina, p.Tezina, p.Fotografija, p.Udomljen, " +
                       "u.JMBG, u.Ime ImeUdomitelja, u.Prezime, u.Adresa, u.BrojTelefona, " +
                       "up.DatumOd, up.IdPsa, up.UdomiteljJMBG, up.DatumDo " +
                       "FROM udomljavanjepsa up " +
                       "JOIN pas p ON up.IdPsa = p.IdPsa " +
                       "JOIN udomitelj u ON up.UdomiteljJMBG = u.JMBG " +
                       "WHERE p.Obrisan = 0";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                // DogDTO dog = new DogDTO(rs.getInt("IdPsa"), rs.getString("Pol"), rs.getString("ImePsa"), rs.getString("Rasa"), rs.getInt("Visina"), rs.getDouble("Tezina"), rs.getDate("DatumRodjenja"), rs.getString("Fotografija"), rs.getBoolean("Udomljen"));
                // FosterParentDTO fosterParent = new FosterParentDTO(rs.getString("JMBG"), rs.getString("ImeUdomitelja"), rs.getString("Prezime"), rs.getString("Adresa"), rs.getString("BrojTelefona"));
                retVal.add(new AdoptingDogDTO(rs.getDate("DatumOd"), rs.getInt("IdPsa"), rs.getString("UdomiteljJMBG"), rs.getDate("DatumDo")));
            }
        } catch (SQLException ex) {
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("MySQLAdoptingDogDao - getAllAdoptings", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }
        return retVal;
    }

}
