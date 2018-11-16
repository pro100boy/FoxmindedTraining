package task_3.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {
    private LocalDate birthday;

    @Builder
    Client(Integer id, String fName, String lName, String phone, String email, LocalDate birthday) {
        super(id, fName, lName, phone, email);
        this.birthday = birthday;
    }

}

