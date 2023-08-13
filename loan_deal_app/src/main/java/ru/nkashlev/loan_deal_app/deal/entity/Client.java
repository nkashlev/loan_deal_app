package ru.nkashlev.loan_deal_app.deal.entity;

import org.hibernate.annotations.Type;
import ru.nkashlev.loan_deal_app.deal.Employment;
import ru.nkashlev.loan_deal_app.deal.Passport;
import ru.nkashlev.loan_deal_app.deal.model.ScoringDataDTO;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Client {
    @Id
    private Long client_id;

    private String last_name;
    private String first_name;
    private String middle_name;
    private LocalDate birth_date;
    private String email;
    @Enumerated(EnumType.STRING)
    private ScoringDataDTO.GenderEnum gender;
    @Enumerated(EnumType.STRING)
    private ScoringDataDTO.MaritalStatusEnum marital_status;
    private Integer dependent_amount;
    @Type(type = "jsonb")
    private Passport passport;
    @Type(type = "jsonb")
    private Employment employment;
    private String account;
}
