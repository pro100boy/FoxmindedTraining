package task_3.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceptionLog extends BaseEntity{
    private Integer clientId;
    private Integer serviceId;
    private Integer masterId;
    private LocalDate logDate;
    private Long price;

    @Builder
    public ReceptionLog(Integer id, Integer clientId, Integer serviceId, Integer masterId, LocalDate logDate, Long price) {
        super(id);
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.masterId = masterId;
        this.logDate = logDate;
        this.price = price;
    }
}
