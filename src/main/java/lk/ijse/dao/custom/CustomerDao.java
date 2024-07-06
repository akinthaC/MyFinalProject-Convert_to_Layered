package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerDao extends CrudDao<CustomerDTO> {
    public  List<String> getStatus();
}
