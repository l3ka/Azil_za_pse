package util;

import data.dao.DAOFactory;

public class AzilUtilities {
    private static DAOFactory daoFactory;

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null)
            daoFactory = DAOFactory.getDAOFactory();
        return daoFactory;
    }
}
