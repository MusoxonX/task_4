package uz.pdp.task_4.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {
    private Integer fromCardId;
    private Integer toCardId;
    private double amount;
    private Date date;
}
