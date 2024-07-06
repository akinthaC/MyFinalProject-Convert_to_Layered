package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor@ToString@Data@AllArgsConstructor
public class AccessoriesOrderDTO {
    private String OrderId;
    private String AccId;
    private int Qty;
    private String Description;
    private String Status;
    private Date date ;
}
