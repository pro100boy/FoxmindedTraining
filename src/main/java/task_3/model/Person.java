package task_3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Person extends BaseEntity {
    private String fName;
    private String lName;
    private String phone;
    private String email;

    public Person(Integer id, String fName, String lName, String phone, String email) {
        super(id);
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }


}
