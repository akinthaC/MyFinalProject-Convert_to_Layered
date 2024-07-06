package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor@Data@ToString
public class FishOrderDTO {
    private String OrderId;
    private String FishId;
    private int Qty;
    private String Description;
    private String Status;
    private Date date ;
}
