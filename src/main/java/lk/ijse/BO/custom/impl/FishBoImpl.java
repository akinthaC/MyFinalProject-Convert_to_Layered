package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.FishBo;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.FishDao;
import lk.ijse.dao.custom.impl.FishDaoImpl;
import lk.ijse.dto.FishDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishBoImpl implements FishBo {
    FishDao fishDao = (FishDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FISH);
    @Override
    public boolean save(FishDTO fish) throws SQLException, ClassNotFoundException {
        return fishDao.save(new Fish(fish.getId(),fish.getName(),fish.getQty(),fish.getNormalPrice(),fish.getWholesaleprice()));
    }

    @Override
    public boolean update(FishDTO fish) throws SQLException, ClassNotFoundException {
        return fishDao.update(new Fish(fish.getId(),fish.getName(),fish.getQty(),fish.getNormalPrice(),fish.getWholesaleprice()));
    }

    @Override
    public FishDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Fish fish = fishDao.searchById(id);
        return new FishDTO(fish.getId(),fish.getName(),fish.getQty(),fish.getNormalPrice(),fish.getWholesaleprice());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return fishDao.delete(id);
    }

    @Override
    public List<FishDTO> getAll() throws SQLException, ClassNotFoundException {
      List<Fish> fishes = fishDao.getAll();
      List<FishDTO> fishDTOs = new ArrayList<>();
      for (Fish fish : fishes) {
          fishDTOs.add(new FishDTO(
                  fish.getId(),
                  fish.getName(),
                  fish.getQty(),
                  fish.getNormalPrice(),
                  fish.getWholesaleprice()
          ));
      }
      return fishDTOs;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return fishDao.getCurrentId();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return fishDao.getIds();
    }

    @Override
    public boolean updateQty1(String fishId, int qty) throws SQLException, ClassNotFoundException {
        return fishDao.updateQty1(fishId, qty);
    }

    @Override
    public boolean updateSupFish(int qty, String id) throws SQLException, ClassNotFoundException {
        return fishDao.updateSupFish(qty, id);
    }

    @Override
    public Map<String, Integer> GetFishDetail() throws SQLException, ClassNotFoundException {
        return fishDao.GetFishDetail();
    }
}
