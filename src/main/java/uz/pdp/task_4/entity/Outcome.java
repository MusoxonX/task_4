package uz.pdp.task_4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outcome {
    private Integer id;

    @ManyToOne
    private Card fromCardId;

    @ManyToOne
    private Integer toCardId;

    private double amount;

    private Date date;

    private double commisionAmount;
}