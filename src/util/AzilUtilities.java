package util;

import data.dao.DAOFactory;
import data.dto.LoggerDTO;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AzilUtilities {

    private static DAOFactory daoFactory;

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null)
            daoFactory = DAOFactory.getDAOFactory();
        return daoFactory;
    }

    public static String getSHA256(String password){
        String passwordHash = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            passwordHash = IntStream.range(0, byteData.length)
                                    .mapToObj(i -> Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1))
                                    .collect(Collectors.joining());
        } catch(Exception ex){
            AzilUtilities.getDAOFactory().getLoggerDAO().insert(new LoggerDTO("AzilUtilities - getSHA256", new Date(Calendar.getInstance().getTime().getTime()), ex.fillInStackTrace().toString()));
        }
        return passwordHash;
    }

}
