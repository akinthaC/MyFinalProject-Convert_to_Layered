package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupFish {
    private String FishId;
    private String supId;
    private Date date ;
    private int Qty;
    private double amount;
}
