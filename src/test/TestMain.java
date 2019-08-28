package test;

import data.dao.mysql.ConnectionPool;
import data.dto.DogDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args){
        testConnection();
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
