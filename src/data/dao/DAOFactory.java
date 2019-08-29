package data.dao;

import data.dao.mysql.MySQLDAOFactory;
import data.dto.DogDTO;

public abstract class DAOFactory {
    public abstract DogDAO getDogDAO();

    public abstract  EmployeeDAO getEmployeeDAO();

    public abstract  AdministratorDAO getAdministratorDAO();

    public abstract  VeterinarinaDAO getVeterinarinaDAO();

    public abstract  ServantDAO getServantDAO();

    public abstract  ContractDAO getContractDAO();

    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
