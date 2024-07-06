package lk.ijse.BO.custom;

import lk.ijse.BO.FishBo;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.custom.FishDao;
import lk.ijse.dao.custom.impl.FishDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FishBoImpl implements FishBo {
    FishDao fishDao = new FishDaoImpl();
    @Override
    public boolean save(Fish fish) throws SQLException, ClassNotFoundException {
        return fishDao.save(fish);
    }

    @Override
    public boolean update(Fish fish) throws SQLException, ClassNotFoundException {
        return fishDao.update(fish);
    }

    @Override
    public Fish searchById(String id) throws SQLException, ClassNotFoundException {
        Fish fish = fishDao.searchById(id);
        return fish;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return fishDao.delete(id);
    }

    @Override
    public List<Fish> getAll() throws SQLException, ClassNotFoundException {
       List<Fish> fishList = fishDao.getAll();
       return fishList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return fishDao.getCurrentId();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
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
