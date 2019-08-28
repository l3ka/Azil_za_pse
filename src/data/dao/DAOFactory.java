package data.dao;

import data.dao.mysql.MySQLDAOFactory;
import data.dto.DogDTO;

public abstract class DAOFactory {
    public abstract DogDAO getDogDAO();

    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
