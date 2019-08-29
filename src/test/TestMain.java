package test;

import data.dao.mysql.ConnectionPool;
import data.dto.*;
import util.AzilUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestMain {
    public static void main(String[] args){
        testServant();
    }

    static void testServant(){
        Date Od = new Date(Calendar.getInstance().getTime().getTime());
        Date Do = new Date(Calendar.getInstance().getTime().getTime());
        ServantDTO servant = new ServantDTO("Radeee", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995001111");
        EmploymentContractDTO contract = new EmploymentContractDTO(null, "Admin", Od, Do, 1000);


        boolean res = AzilUtilities.getDAOFactory().getServantDAO().insert(servant, contract);

        if(res){
            System.out.println("Success");
        }else {
            System.out.println("Faild");
        }
    }

    static void testVet(){
        Date Od = new Date(Calendar.getInstance().getTime().getTime());
        Date Do = new Date(Calendar.getInstance().getTime().getTime());
        VeterinarianDTO vet = new VeterinarianDTO("Radeee", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995011111");
        EmploymentContractDTO contract = new EmploymentContractDTO(null, "Admin", Od, Do, 1000);


        boolean res = AzilUtilities.getDAOFactory().getVeterinarinaDAO().insert(vet, contract);

        if(res){
            System.out.println("Success");
        }else {
            System.out.println("Faild");
        }
    }

    static void testAdmin(){
        Date Od = new Date(Calendar.getInstance().getTime().getTime());
        Date Do = new Date(Calendar.getInstance().getTime().getTime());
        AdministratorDTO admin = new AdministratorDTO("Rade95", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995111111");
        EmploymentContractDTO contract = new EmploymentContractDTO(null, "Admin", Od, Do, 1000);


        boolean res = AzilUtilities.getDAOFactory().getAdministratorDAO().insert(admin, contract);

        if(res){
            System.out.println("Success");
        }else {
            System.out.println("Faild");
        }
    }

    static void testDelete(){
        List<DogDTO> dogs = AzilUtilities.getDAOFactory().getDogDAO().dogs();

        DogDTO dog = dogs.get(0);

        boolean res = AzilUtilities.getDAOFactory().getDogDAO().delete(dog.getDogId());

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
