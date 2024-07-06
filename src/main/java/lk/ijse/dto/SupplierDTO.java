package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SupplierDTO {
    private String id;
    private String name;
    private String contact;
    private String NIC;
    private String address;

}
