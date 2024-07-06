package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessoriesDTO {
    private String id;
    private String name ;
    private String Qty;
    private String normalPrice;
    private String wholesaleprice;

}
