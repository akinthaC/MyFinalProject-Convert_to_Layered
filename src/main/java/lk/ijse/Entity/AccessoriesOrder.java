package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor@AllArgsConstructor@Data@ToString
public class AccessoriesOrder {
    private String OrderId;
    private String AccId;
    private int Qty;
    private String Description;
    private String Status;
    private Date date ;
}
