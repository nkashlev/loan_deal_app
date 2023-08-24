package ru.nkashlev.loan_deal_app.deal.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nkashlev.loan_deal_app.deal.model.ApplicationStatusHistoryDTO;

import java.io.Serializable;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class StatusHistory implements Serializable {
    private ApplicationStatusHistoryDTO.StatusEnum status;
    private LocalDate time;
    private ApplicationStatusHistoryDTO.ChangeTypeEnum change_type;
}
