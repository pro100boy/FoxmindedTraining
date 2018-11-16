package task_3.model;

import lombok.*;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class Service extends BaseEntity{
    private String name;
    private Long price;
    private Integer duration;

    @Builder
    public Service(Integer id, String name, Long price, Integer duration) {
        super(id);
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
}
