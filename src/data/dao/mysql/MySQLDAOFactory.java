package data.dao.mysql;

import data.dao.*;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public DogDAO getDogDAO(){
        return new MySQLDogDAO();
    }

    @Override
    public EmployeeDAO getEmployeeDAO(){ return new MySQLEmployeeDAO(); }

    @Override
    public VeterinarianDAO getVeterinarinaDAO() { return  new MySQLVeterinarianDAO(); }

    @Override
    public ServantDAO getServantDAO() { return new MySQLServantDAO(); }

    @Override
    public AdministratorDAO getAdministratorDAO() { return new MySQLAdministratorDAO();}

    @Override
    public ContractDAO getContractDAO(){ return new MySQLContractDAO(); }

    @Override
    public FosterParentDAO getFosterParentDAO(){ return  new MySQLFosterParentDAO(); }

    @Override
    public AdoptingDAO getAdoptingDAO() { return new MySQLAdoptingDAO(); }

    @Override
    public CageDAO getCageDAO() { return new MySQLCageDAO(); }

    @Override
    public DogInCageDAO getDogInCageDAO() { return new MySQLDogInCage(); }

    @Override
    public MedicalResultDAO getMedicalResultDAO() { return new MySQLMedicalResultDAO(); }

    @Override
    public MedicineDAO getMedicineDAO() { return  new MySQLMedicineDAO(); }

    @Override
    public TakingMedicineDAO getTakingMedicineDAO() { return new MySQLTakingMedicineDAO(); }

}
