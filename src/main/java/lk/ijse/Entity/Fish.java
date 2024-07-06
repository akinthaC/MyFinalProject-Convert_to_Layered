package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Fish {
    private String id;
    private String name ;
    private String Qty;
    private double normalPrice;
    private double wholesaleprice;


}
