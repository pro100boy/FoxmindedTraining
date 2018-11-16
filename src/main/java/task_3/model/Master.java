package task_3.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class Master extends Person{
    private Integer serviceId;

    @Builder
    public Master(Integer id, String fName, String lName, String phone, String email, Integer serviceId) {
        super(id, fName, lName, phone, email);
        this.serviceId = serviceId;
    }
}
