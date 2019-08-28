package test;

import data.dao.mysql.ConnectionPool;
import data.dto.DogDTO;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args){
        testDelete();
    }

    static void testDelete(){
        List<DogDTO> dogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

        DogDTO dog = dogs.get(0);

        boolean res = AzilUtilities.getDAOFactory().getDogDAO().delete(dog.getId());

        if(res){
            System.out.println("Succes...");
        }else {
            System.out.println("Faild...");
        }
    }
    static void testUpdate(){
        List<DogDTO> dogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

        DogDTO dog = dogs.get(0);
        dog.setName("Dzeki");

        AzilUtilities.getDAOFactory().getDogDAO().update(dog);
    }
    static void testInsert(){
        DogDTO dog = new DogDTO(null, "M", "Bobi", "Mjesanac", 50, 12.0, null,
                "resources/images.bobi.jpg");
        boolean res = AzilUtilities.getDAOFactory().getDogDAO().insert(dog);

        if(res){
            System.out.println("Success...");
        } else {
            System.out.println("Faild...");
        }
    }

    static  void testRead(){
        List<DogDTO> dogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

        for(DogDTO dog : dogs){
            System.out.println(dog.getName());
        }
    }

    private  static void  testConnection(){
        List<DogDTO> dogs = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM pas";

        try {


            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("Ime"));
            }
//                dogs.add(new FakultetDTO(rs.getString("NazivFakulteta"), rs
//                        .getString("Adresa")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
