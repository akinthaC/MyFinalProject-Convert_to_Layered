package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.CustomerBo;
import lk.ijse.Entity.Customer;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(customer.getId(),customer.getName(),customer.getContact(),customer.getNIC(),customer.getAddress(),customer.getType()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(customer.getId(),customer.getName(),customer.getContact(),customer.getNIC(),customer.getAddress(),customer.getType()));
    }

    @Override
    public CustomerDTO CusSearchById(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDao.searchById(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getContact(),customer.getNIC(),customer.getAddress(),customer.getType());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> accessoriesDTOArrayList= customerDao.getAll();

        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer : accessoriesDTOArrayList) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getNIC(),
                    customer.getAddress(),
                    customer.getType()

            ));
        }
        return customerDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDao.getCurrentId();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return customerDao.getIds();
    }

    @Override
    public List<String> getStatus() {
        return customerDao.getStatus();
    }
}
