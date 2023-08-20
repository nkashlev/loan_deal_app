package ru.nkashlev.loan_deal_app.deal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.nkashlev.loan_deal_app.deal.entity.util.StatusHistory;
import ru.nkashlev.loan_deal_app.deal.model.ApplicationStatusHistoryDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long application_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatusHistoryDTO.StatusEnum status;

    @Column(name = "creation_date")
    private LocalDate creation_date;

    @Type(type = "jsonb")
    @Column(name = "applied_offer")
    private String applied_offer; /// applied_offer jsonb, но в таблице нет ссылки на др таблицу //todo

    @Column(name = "sign_date")
    private LocalDate sign_date;

    @Column(name = "ses_code")
    private Long ses_code;

    @Type(type = "jsonb")
    @Column(name = "status_history")
    private StatusHistory status_history;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;
}
