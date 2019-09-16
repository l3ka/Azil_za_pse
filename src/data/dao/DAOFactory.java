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

    public abstract  MedicalResultDAO getMedicalResultDAO();

    public abstract  MedicineDAO getMedicineDAO();

    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }

    public abstract TakingMedicineDAO getTakingMedicineDAO();

    public abstract LoggerDAO getLoggerDAO();

    public abstract PdfExporterDAO getPdfExporterDAO();

    public abstract EmployeeContractDAO getEmployeeContractDAO();

    public abstract AdoptingDogDAO getAdoptingDogDAO();

    public abstract EmploymentContractDAO getEmploymentContractDAO();

}
