package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.SupFishBo;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.SupFishDao;
import lk.ijse.dao.custom.impl.SupFisDaoImpl;
import lk.ijse.dto.SupFishDTO;

import java.sql.SQLException;

public class SupFishBoImpl implements SupFishBo {
    SupFishDao supFishDao =(SupFishDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPFISH);;;
    @Override
    public boolean save(SupFishDTO supFish) throws SQLException, ClassNotFoundException {
        return supFishDao.save(new SupFish(supFish.getSupId(),supFish.getFisId(), supFish.getDate(), supFish.getQty(),supFish.getAmount()));
    }
}
