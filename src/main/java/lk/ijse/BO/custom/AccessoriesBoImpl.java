package lk.ijse.BO.custom;

import lk.ijse.BO.AccessoriesBo;
import lk.ijse.Entity.Accessories;
import lk.ijse.dao.custom.AccessoriesDao;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.impl.AccessoriesDaoImpl;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.dto.AccessoriesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessoriesBoImpl implements AccessoriesBo {
    AccessoriesDao accessoriesDao=new AccessoriesDaoImpl();
    @Override
    public boolean save(AccessoriesDTO accessories) throws SQLException, ClassNotFoundException {
       return accessoriesDao.save(new Accessories(accessories.getId(), accessories.getName(), accessories.getQty(), accessories.getNormalPrice(), accessories.getWholesaleprice()));
    }

    @Override
    public boolean update(AccessoriesDTO accessories) throws SQLException, ClassNotFoundException {
        return accessoriesDao.update(new Accessories(accessories.getId(), accessories.getName(), accessories.getQty(), accessories.getNormalPrice(), accessories.getWholesaleprice() ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return accessoriesDao.delete(id);
    }

    @Override
    public List<AccessoriesDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Accessories> accessoriesDTOArrayList= accessoriesDao.getAll();

        List<AccessoriesDTO> accessoriesDTOList=new ArrayList<>();
        for (Accessories accessories : accessoriesDTOArrayList) {
            accessoriesDTOList.add(new AccessoriesDTO(
                    accessories.getId(),
                    accessories.getName(),
                    accessories.getQty(),
                    accessories.getNormalPrice(),
                    accessories.getWholesaleprice()
            ));
        }
    return accessoriesDTOList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return accessoriesDao.getCurrentId();
    }

    @Override
    public AccessoriesDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Accessories accessories = accessoriesDao.searchById(id);
        return new AccessoriesDTO(accessories.getId(), accessories.getName(), accessories.getQty(), accessories.getNormalPrice(), accessories.getWholesaleprice());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return accessoriesDao.getIds();
    }

    @Override
    public boolean updateQty(String accId, int qty) throws SQLException, ClassNotFoundException {
        return accessoriesDao.updateQty(accId, qty);
    }

    @Override
    public boolean updateSupAcc(int qty, String accID) throws SQLException, ClassNotFoundException {
        return accessoriesDao.updateSupAcc(qty, accID);
    }
}
