package util;

import data.dao.DAOFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AzilUtilities {

    private static DAOFactory daoFactory;

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null)
            daoFactory = DAOFactory.getDAOFactory();
        return daoFactory;
    }

    public static  String getSHA256_OLD(String text){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(digest.digest(text.getBytes(StandardCharsets.UTF_8)));
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
        } catch(Exception e){
            e.printStackTrace();
        }
        return passwordHash;
    }

}
