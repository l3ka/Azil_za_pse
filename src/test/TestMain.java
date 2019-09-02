package test;

import data.dao.FosterParentDAO;
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
        testDeleteFosterParent("0202993199942");
    }

    public static void testDeleteFosterParent(String JMB){
        AzilUtilities.getDAOFactory().getFosterParentDAO().delete(JMB);
    }

    public static void testUpdateAdopting(){
        FosterParentDTO fosterParent1 = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents().get(0);
        FosterParentDTO fosterParent2 = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents().get(1);
        AdoptingDTO adopting = AzilUtilities.getDAOFactory().getAdoptingDAO().getAdoptingForFosterParent(fosterParent1).get(0);

        AzilUtilities.getDAOFactory().getAdoptingDAO().update(fosterParent2, adopting, adopting.getDateFrom(),
                adopting.getDog().getDogId(), fosterParent1.getJMB());
    }

    public static void testGetAdpopting(){
        FosterParentDTO fosterParent = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents().get(0);
        List<AdoptingDTO> adoptings = AzilUtilities.getDAOFactory().getAdoptingDAO().getAdoptingForFosterParent(fosterParent);

        for(AdoptingDTO adopting : adoptings){
            System.out.println(adopting.getDog().getDogId() +  " " + adopting.getDog().getName());
        }
    }

    public static void testAdopting(){
        FosterParentDTO fosterParent = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents().get(0);
        DogDTO dog = AzilUtilities.getDAOFactory().getDogDAO().dogs().get(1);
        AdoptingDTO adopting = new AdoptingDTO(
                dog,
                new Date(Calendar.getInstance().getTime().getTime()),
                new Date(Calendar.getInstance().getTime().getTime())
        );

        AzilUtilities.getDAOFactory().getAdoptingDAO().insert(fosterParent, adopting);
    }

    public static  void testFosterParentUpdate(){
        FosterParentDTO fosterParent = AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents().get(0);
        fosterParent.setName("NovoIme");
        AzilUtilities.getDAOFactory().getFosterParentDAO().update(fosterParent);
    }


    public static void testFosterParents(){
        for(FosterParentDTO fosterParent : AzilUtilities.getDAOFactory().getFosterParentDAO().fosterParents()){
            System.out.println(fosterParent.getName());
        }
    }

    public static void testFosterParentInsert(){
        FosterParentDTO fosterParent = new FosterParentDTO(
                "0202993199942",
                "Ime2",
                "Prezime2",
                "Adres",
                "Broj");

        AzilUtilities.getDAOFactory().getFosterParentDAO().insert(fosterParent);
    }

    public static void testAdminLogin(String usernam, String password){
        AdministratorDTO admin = AzilUtilities.getDAOFactory().getAdministratorDAO().login(usernam, password);

        System.out.println(admin.getName());

    }

    public static  void testContractUpdate(String username){
        EmploymentContractDTO contract = AzilUtilities.getDAOFactory().getContractDAO().contractsForEmployee(
                AzilUtilities.getDAOFactory().getAdministratorDAO().getByUsername(username)).get(0);

        contract.setSalary(2000);

        AzilUtilities.getDAOFactory().getContractDAO().update(contract);
    }
    public static void testDeleteAdmin(String username){
        AdministratorDTO admin = AzilUtilities.getDAOFactory().getAdministratorDAO().getByUsername(username);
        boolean res = AzilUtilities.getDAOFactory().getAdministratorDAO().delete(admin);

        if(res){
            System.out.println("Success");
        } else {
            System.out.println("Faild");
        }
    }

    public static void testAdministrators(){
        List<AdministratorDTO> admnis = AzilUtilities.getDAOFactory().getAdministratorDAO().adminstartors();
        System.out.println(admnis.size());
        System.out.println(admnis.get(0).getName());
    }

    public static void  testUpdateAdminWithJMB(){
        AdministratorDTO admin = AzilUtilities.getDAOFactory().getAdministratorDAO().getByUsername("Admin");
        String oldJMB = admin.getJMB();
        admin.setJMB("0203995111199");
        AzilUtilities.getDAOFactory().getAdministratorDAO().updateWithJMB(admin, oldJMB);
    }
    public static  void testUpdateAdmin(){
        AdministratorDTO admin = AzilUtilities.getDAOFactory().getAdministratorDAO().getByUsername("Admin");
        admin.setName("AdminIme");
        AzilUtilities.getDAOFactory().getAdministratorDAO().update(admin);
    }
    static void testGetServant(String username){
        ServantDTO servant = AzilUtilities.getDAOFactory().getServantDAO().getByUsername(username);

        if(servant != null){
            System.out.println(servant.getPassword());
        } else {
            System.out.println("Doesn't exist...");
        }
    }

    static void testGetVet(String username){
        VeterinarianDTO vet = AzilUtilities.getDAOFactory().getVeterinarinaDAO().getByUsername(username);

        if(vet != null){
            System.out.println(vet.getPassword());
        } else {
            System.out.println("Doesn't exist...");
        }
    }

    static void testGetAdmin(String username){
        AdministratorDTO admin = AzilUtilities.getDAOFactory().getAdministratorDAO().getByUsername(username);
        if(admin != null){
            System.out.println(admin.getPassword());
        } else {
            System.out.println("Doesn't exist...");
        }
    }
    static void testServant(){
        Date Od = new Date(Calendar.getInstance().getTime().getTime());
        Date Do = new Date(Calendar.getInstance().getTime().getTime());
        ServantDTO servant = new ServantDTO("Sluzbenik", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995001121");
        EmploymentContractDTO contract = new EmploymentContractDTO(null,1, "Sluzbenik", Od, Do, 1000);


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
        VeterinarianDTO vet = new VeterinarianDTO("Veterinar", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995011111");
        EmploymentContractDTO contract = new EmploymentContractDTO(null, 1, "Admin", Od, Do, 1000);


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
        AdministratorDTO admin = new AdministratorDTO("Admin1", "Password", "Rade", "Culum", "kvalifikacija",
                "adres", "broj", "0203995111112");
        EmploymentContractDTO contract = new EmploymentContractDTO(null, 1,"Admin", Od, Do, 1000);


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

        boolean res = AzilUtilities.getDAOFactory().getDogDAO().delete(dog);

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
