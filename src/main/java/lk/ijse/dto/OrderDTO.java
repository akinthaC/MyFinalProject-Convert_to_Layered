package lk.ijse.dto;

import lk.ijse.model.tm.cartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDTO extends cartTm {
    private String id;
    private Date date ;
    private Date handOverDate;
    private int Qty;
    private String CusId;
}
