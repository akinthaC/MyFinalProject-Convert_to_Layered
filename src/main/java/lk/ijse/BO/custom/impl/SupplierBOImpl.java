package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.SupplierBo;
import lk.ijse.Entity.Supplier;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.SupplierDao;
import lk.ijse.dao.custom.impl.SupplierDaoImpl;
import lk.ijse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBo {
    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);;
    @Override
    public String getId(String value) throws SQLException, ClassNotFoundException {
        return supplierDao.getId(value);
    }

    @Override
    public List<String> searchNIC() throws SQLException, ClassNotFoundException {
        return supplierDao.searchNIC();
    }

    @Override
    public SupplierDTO searchByNIC(String nic) throws SQLException, ClassNotFoundException {
        Supplier supplier= supplierDao.searchByNIC(nic);
        return new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getNIC(),supplier.getAddress());
    }

    @Override
    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDao.save(new Supplier(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getNIC(),supplier.getAddress()));
    }

    @Override
    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDao.update(new Supplier(supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getNIC(),supplier.getAddress()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.delete(id);
    }

    @Override
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> supplierList = supplierDao.getAll();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            supplierDTOList.add(new SupplierDTO(
                    supplier.getId(),
                    supplier.getName()
                    ,supplier.getContact()
                    ,supplier.getNIC(),
                    supplier.getAddress()
            ));
        }
        return supplierDTOList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return supplierDao.getCurrentId();
    }

    @Override
    public Supplier searchById(String id) throws SQLException, ClassNotFoundException {
        return supplierDao.searchById(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return supplierDao.getIds();
    }
}
