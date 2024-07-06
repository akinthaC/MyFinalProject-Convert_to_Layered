package lk.ijse.dao.custom;

import lk.ijse.Entity.Customer;
import lk.ijse.dao.CrudDao;
import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerDao extends CrudDao<Customer> {
    public  List<String> getStatus();
}
