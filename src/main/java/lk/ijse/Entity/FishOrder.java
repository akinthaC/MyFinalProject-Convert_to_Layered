package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishOrder {
    private String OrderId;
    private String FishId;
    private int Qty;
    private String Description;
    private String Status;
    private Date date ;




}
