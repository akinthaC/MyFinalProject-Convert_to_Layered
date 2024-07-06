package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SupAcc {
    private String AccId;
    private String supId;
    private Date date ;
    private int Qty;
    private double amount;

}
