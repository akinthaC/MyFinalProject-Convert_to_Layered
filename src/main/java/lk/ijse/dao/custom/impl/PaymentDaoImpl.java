package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Payment;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDao;
import lk.ijse.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    public  boolean save(Payment payment) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL( "INSERT INTO payment VALUES(?,?,?,?,?,?,?,?) ",payment.getId(),payment.getOrdid(),payment.getDate(),payment.getTotal(),payment.getAdvance(),payment.getType(),payment.getStatus(),payment.getAmountToPaid());

    }

    public  boolean update(Payment payment) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE payment SET orderId=?, date= ?, totalAmounr = ?, advance = ?, paytype = ? ,status =? , amountToBePaid =? WHERE payId = ?",payment.getOrdid(),payment.getDate(),payment.getTotal(),payment.getAdvance(),payment.getType(),payment.getStatus(),payment.getAmountToPaid(),payment.getId());
    }



    public  boolean delete(String id) throws SQLException, ClassNotFoundException {


        return SQLUtil.executeSQL("DELETE FROM payment WHERE payId = ?",id);
    }

    public  List<Payment> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeSQL("SELECT * FROM payment");

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String payid = resultSet.getString(1);
            String ordid = resultSet.getString(2);
            String date = resultSet.getString(3);
            double total = Double.parseDouble(resultSet.getString(4));
            double advance = Double.parseDouble(resultSet.getString(5));
            String type = resultSet.getString(6);
            String status = resultSet.getString(7);
            double amountToPay = Double.parseDouble(resultSet.getString(8));


            Payment payment= new Payment(payid,ordid,date,total,advance,type,amountToPay,status);
            paymentList.add(payment);
        }
        return paymentList;


    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeSQL( "SELECT payId FROM payment ORDER BY payId DESC LIMIT 1");
        if(resultSet.next()) {
            String payId = resultSet.getString(1);
            return payId;
        }
        return null;
    }



    public  Payment searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM payment WHERE payid = ?",id);
        if (resultSet.next()) {
            String payid = resultSet.getString(1);
            String ordid = resultSet.getString(2);
            String date = resultSet.getString(3);
            double total = Double.parseDouble(resultSet.getString(4));
            double advance = Double.parseDouble(resultSet.getString(5));
            String type = resultSet.getString(6);
            String status = resultSet.getString(7);
            double amountToPay = Double.parseDouble(resultSet.getString(8));


            Payment payment= new Payment(payid,ordid,date,total,advance,type,amountToPay,status);

            return payment;
        }

        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT id FROM orders");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public  List<String> getType() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("Cash");
        obList.add("Card");

        return obList;
    }

    public  PaymentDTO searchByOrId(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM payment WHERE orderId = ?",id);
        if (resultSet.next()) {
            String payid = resultSet.getString(1);
            String ordid = resultSet.getString(2);
            String date = resultSet.getString(3);
            double total = Double.parseDouble(resultSet.getString(4));
            double advance = Double.parseDouble(resultSet.getString(5));
            String type = resultSet.getString(6);
            String status = resultSet.getString(7);
            double amountToPay = Double.parseDouble(resultSet.getString(8));


            PaymentDTO payment= new PaymentDTO(payid,ordid,date,total,advance,type,amountToPay,status);

            return payment;
        }

        return null;
    }

    public  List<String> getOrIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet =SQLUtil.executeSQL("SELECT orderId FROM payment");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public  boolean update1(String id, double advance1, double amountToPaid, String status) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE payment SET  advance = ?,status =? , amountToBePaid =? WHERE payId = ?",advance1,status,amountToPaid,id);
    }
}
