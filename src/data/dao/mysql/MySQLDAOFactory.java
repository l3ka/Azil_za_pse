package data.dao.mysql;

import data.dao.DAOFactory;
import data.dao.DogDAO;
import data.dto.DogDTO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public DogDAO getDogDAO(){
        return new MySQLDogDAO();
    }
}
