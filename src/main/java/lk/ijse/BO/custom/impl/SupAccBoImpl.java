package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.SupAccBo;
import lk.ijse.Entity.SupAcc;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.SupAccDao;
import lk.ijse.dao.custom.impl.SupAccDaoImpl;
import lk.ijse.dto.SupAccDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupAccBoImpl implements SupAccBo {
    SupAccDao supAccDao= (SupAccDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPACC);;
    @Override
    public List<SupAccDTO> getAll() throws SQLException, ClassNotFoundException {
        List<SupAcc> supAccList = supAccDao.getAll();
        List<SupAccDTO> supAccDTOList = new ArrayList<>();
        for (SupAcc supAcc : supAccList) {
            supAccDTOList.add(new SupAccDTO(
                    supAcc.getAccId(),
                    supAcc.getSupId(),
                    supAcc.getDate(),
                    supAcc.getQty(),
                    supAcc.getAmount()
            ));
        }
        return supAccDTOList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return supAccDao.getIds();
    }

    @Override
    public boolean save(SupAccDTO supAcc) throws SQLException, ClassNotFoundException {
        return supAccDao.save(new SupAcc(supAcc.getAccId(), supAcc.getSupId(), supAcc.getDate(), supAcc.getQty(), supAcc.getAmount()));
    }
}
