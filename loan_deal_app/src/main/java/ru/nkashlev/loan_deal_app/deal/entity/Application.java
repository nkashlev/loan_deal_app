package ru.nkashlev.loan_deal_app.deal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.nkashlev.loan_deal_app.deal.model.ApplicationStatusHistoryDTO;
import ru.nkashlev.loan_deal_app.deal.model.LoanOfferDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private LoanOfferDTO applied_offer;

    @Column(name = "sign_date")
    private LocalDate sign_date;

    @Column(name = "ses_code")
    private Long ses_code;

    @Type(type = "jsonb")
    @Column(name = "status_history")
    private List<ApplicationStatusHistoryDTO> status_history;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;
}
