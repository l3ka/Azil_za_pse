package data.dao;

import data.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
    public abstract DogDAO getDogDAO();

    public abstract  EmployeeDAO getEmployeeDAO();

    public abstract  AdministratorDAO getAdministratorDAO();

    public abstract VeterinarianDAO getVeterinarinaDAO();

    public abstract  ServantDAO getServantDAO();

    public abstract  ContractDAO getContractDAO();

    public abstract  FosterParentDAO getFosterParentDAO();

    public abstract AdoptingDAO getAdoptingDAO();

    public abstract CageDAO getCageDAO();

    public abstract DogInCageDAO getDogInCageDAO();

    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
}
