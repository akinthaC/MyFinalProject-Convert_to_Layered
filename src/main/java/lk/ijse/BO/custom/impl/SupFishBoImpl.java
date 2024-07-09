package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.SupFishBo;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.SupFishDao;
import lk.ijse.dao.custom.impl.SupFisDaoImpl;
import lk.ijse.dto.SupFishDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupFishBoImpl implements SupFishBo {
    SupFishDao supFishDao =(SupFishDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPFISH);
    @Override
    public boolean save(SupFishDTO supFish) throws SQLException, ClassNotFoundException {
        return supFishDao.save(new SupFish(supFish.getFisId(),supFish.getSupId(), supFish.getDate(), supFish.getQty(),supFish.getAmount()));
    }

    @Override
    public List<SupFishDTO> getAll() throws SQLException, ClassNotFoundException {
        List<SupFish> supFishList = supFishDao.getAll();
        List<SupFishDTO> supFishDTOS=new ArrayList<>();
        for (SupFish supFish : supFishList) {
            supFishDTOS.add(new SupFishDTO(
                    supFish.getFishId(),
                    supFish.getSupId(),
                    (Date) supFish.getDate(),
                    supFish.getQty(),
                    supFish.getAmount()
            ));
        }
        return supFishDTOS;
    }
}
