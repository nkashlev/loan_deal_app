package ru.nkashlev.loan_deal_app.deal.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nkashlev.loan_deal_app.deal.model.EmploymentDTO;

import java.math.BigDecimal;
@RequiredArgsConstructor
@Getter
@Setter
public class Employment {
    private EmploymentDTO.EmploymentStatusEnum status;
    private String employer_inn;
    private BigDecimal salary;
    private EmploymentDTO.PositionEnum position;
    private Integer work_experience_total;
    private Integer work_experience_current;
}
