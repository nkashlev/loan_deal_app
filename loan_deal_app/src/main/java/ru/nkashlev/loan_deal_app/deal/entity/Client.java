package ru.nkashlev.loan_deal_app.deal.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.nkashlev.loan_deal_app.deal.entity.util.Employment;
import ru.nkashlev.loan_deal_app.deal.entity.util.Passport;
import ru.nkashlev.loan_deal_app.deal.model.ScoringDataDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long client_id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private ScoringDataDTO.GenderEnum gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private ScoringDataDTO.MaritalStatusEnum marital_status;

    @Column(name = "dependent_amount")
    private Integer dependent_amount;

    @Type(type = "jsonb")
    @Column(name = "passport")
    private Passport passport;

    @Type(type = "jsonb")
    @Column(name = "employment")
    private Employment employment;

    @Column(name = "account")
    private String account;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
