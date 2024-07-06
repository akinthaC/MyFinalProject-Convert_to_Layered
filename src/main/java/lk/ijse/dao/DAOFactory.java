package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory==null) ?  new DAOFactory(): daoFactory;
    }

    public enum DAOType{
        ACCESSORIES,ACCESSORIESORDER,CUSTOMER,DASHBOARD,EMPLOYEE,FISH,FISHORDER,ORDER,ORDERDETAIL,OREDREMPLOYEE,PAYMENT,Query,SUPACC,SUPFISH,SUPPLIER,USER
    }
    public SuperDao getDAO(DAOType daoType){
        switch (daoType) {
            case ACCESSORIES:
                return new AccessoriesDaoImpl();
            case ACCESSORIESORDER:
                return new AccessoriesDaoImpl();
            case CUSTOMER:
                return new CustomerDaoImpl();
            case DASHBOARD:
                return new DashBoardDaoImpl();
            case EMPLOYEE:
                 return new EmployeeDaoImpl();
            case FISH:
                 return new FishDaoImpl();
            case FISHORDER:
                 return new FishOrderDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDERDETAIL:
                return new OrderDetailDaoImpl();
            case OREDREMPLOYEE:
                return new OrderEmployeeDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case Query:
                return new QueryDaoImpl();
            case SUPACC:
                return new SupAccDaoImpl();
            case SUPFISH:
                return new SupFisDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case USER:
                return new UserDaoImpl();
            default:
                return null;

        }

    }
}
