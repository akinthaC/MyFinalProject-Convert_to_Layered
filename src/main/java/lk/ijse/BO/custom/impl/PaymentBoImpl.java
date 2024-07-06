package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.Entity.Payment;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.PaymentDao;
import lk.ijse.dao.custom.impl.PaymentDaoImpl;
import lk.ijse.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO{
    PaymentDao paymentDao =(PaymentDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);;;
    @Override
    public boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDao.save(new Payment(payment.getId(), payment.getOrdid(), payment.getDate(),payment.getTotal(),payment.getAdvance(),payment.getType(),payment.getAmountToPaid(),payment.getStatus()));
    }

    @Override
    public boolean update(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDao.update(new Payment(payment.getId(),payment.getOrdid(),payment.getDate(),payment.getTotal(),payment.getAdvance(),payment.getType(),payment.getAmountToPaid(),payment.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDao.delete(id);
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
       List<Payment> payments = paymentDao.getAll();
       List<PaymentDTO> paymentDTOs = new ArrayList<>();
       for (Payment payment : payments) {
           paymentDTOs.add(new PaymentDTO(
                   payment.getId(),
                   payment.getOrdid(),
                   payment.getDate(),
                   payment.getTotal(),
                   payment.getAdvance(),
                   payment.getType(),
                   payment.getAmountToPaid(),
                   payment.getStatus()
           ));
       }
       return paymentDTOs;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
       return paymentDao.getCurrentId();
    }

    @Override
    public PaymentDTO searchById(String id) throws SQLException, ClassNotFoundException {
       Payment payment = paymentDao.searchById(id);
       return new PaymentDTO(payment.getId(),payment.getOrdid(),payment.getDate(),payment.getTotal(),payment.getAdvance(),payment.getType(),payment.getAmountToPaid(),payment.getStatus());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return paymentDao.getIds();
    }

    @Override
    public List<String> getType() {
        return paymentDao.getType();
    }

    @Override
    public PaymentDTO searchByOrId(String id) throws SQLException, ClassNotFoundException {
        return paymentDao.searchByOrId(id);
    }

    @Override
    public List<String> getOrIds() throws SQLException, ClassNotFoundException {
        return paymentDao.getOrIds();
    }

    @Override
    public boolean update1(String id, double advance1, double amountToPaid, String status) throws SQLException, ClassNotFoundException {
        return paymentDao.update1(id,advance1,amountToPaid,status);
    }
}
