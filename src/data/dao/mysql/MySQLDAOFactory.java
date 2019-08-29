package data.dao.mysql;

import data.dao.*;
import data.dto.DogDTO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public DogDAO getDogDAO(){
        return new MySQLDogDAO();
    }

    @Override
    public EmployeeDAO getEmployeeDAO(){ return new MySQLEmployeeDAO(); }

    @Override
    public  VeterinarinaDAO getVeterinarinaDAO() { return  new MySQLVeterinarinaDAO(); }

    @Override
    public ServantDAO getServantDAO() { return new MySQLServantDAO(); }

    @Override
    public AdministratorDAO getAdministratorDAO() { return new MySQLAdministratorDAO();}

    @Override
    public ContractDAO getContractDAO(){ return new MySQLContractDAO(); }
}
