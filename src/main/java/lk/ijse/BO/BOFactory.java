package lk.ijse.BO;

import lk.ijse.BO.custom.impl.*;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SuperDao;
import lk.ijse.dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }

    public enum BOType{
        ACCESSORIES,ACCESSORIESORDER,CUSTOMER,DASHBOARD,EMPLOYEE,FISH,FISHORDER,ORDER,ORDERDETAIL,OREDREMPLOYEE,PAYMENT,PLACEORDER,SUPACC,SUPFISH,SUPPLIER,USER
    }
    public SuperBo GetBo(BOType boType){
        switch (boType) {
            case ACCESSORIES:
                return new AccessoriesBoImpl();
            case ACCESSORIESORDER:
                return new AccessoriesBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();
            case DASHBOARD:
                return new DashboardBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case FISH:
                return new FishBoImpl();
            case FISHORDER:
                return new FishOrderBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case ORDERDETAIL:
                return new OrderDetailBoImpl();
            case OREDREMPLOYEE:
                return new OrderEmployeeBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case PLACEORDER:
                return new PlaceOrderBoImpl();
            case SUPACC:
                return new SupAccBoImpl();
            case SUPFISH:
                return new SupFishBoImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBoImpl();
            default:
                return null;

        }

    }
}
