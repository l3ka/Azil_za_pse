package data.dao;

import data.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
    public abstract DogDAO getDogDAO();

    public abstract  EmployeeDAO getEmployeeDAO();

    public abstract  AdministratorDAO getAdministratorDAO();

    public abstract VeterinarianDAO getVeterinarinaDAO();

    public abstract  ServantDAO getServantDAO();

    public abstract  ContractDAO getContractDAO();

    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
