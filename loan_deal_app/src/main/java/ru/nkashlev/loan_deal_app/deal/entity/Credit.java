package ru.nkashlev.loan_deal_app.deal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.nkashlev.loan_deal_app.deal.entity.util.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long credit_id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "term")
    private Integer term;
    @Column(name = "monthly_payment")
    private BigDecimal monthly_payment;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "psk")
    private BigDecimal psk;
    @Type(type = "jsonb")
    @Column(name = "payment_schedule")/// payment_schedule jsonb, но в таблице нет ссылки на др таблицу.
    private String payment_schedule;
    @Column(name = "insurance_enable")
    private Boolean insurance_enable;
    @Column(name = "salary_client")
    private Boolean salary_client;
    @Column(name = "credit_status")
    private CreditStatus credit_status;
    @OneToOne(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Application application;
}
