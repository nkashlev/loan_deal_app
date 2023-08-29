package ru.nkashlev.loan_deal_app.deal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.nkashlev.loan_deal_app.deal.entity.Application;
import ru.nkashlev.loan_deal_app.deal.entity.Client;
import ru.nkashlev.loan_deal_app.deal.entity.Credit;
import ru.nkashlev.loan_deal_app.deal.entity.util.Passport;
import ru.nkashlev.loan_deal_app.deal.exceptions.ResourceNotFoundException;
import ru.nkashlev.loan_deal_app.deal.model.CreditDTO;
import ru.nkashlev.loan_deal_app.deal.model.FinishRegistrationRequestDTO;
import ru.nkashlev.loan_deal_app.deal.model.ScoringDataDTO;
import ru.nkashlev.loan_deal_app.deal.repositories.ApplicationRepository;
import ru.nkashlev.loan_deal_app.deal.repositories.CreditRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//class CalculateServiceTest {
//
//    @Mock
//    private FinishRegistrationRequestDTO request;
//    @Mock
//    private Application application;
//    @Mock
//    private Client client;
//    @Mock
//    private Passport passport;
//    @Mock
//    private ApplicationRepository applicationRepositoryMock;
//    @Mock
//    private ConveyorCalculationClient conveyorCalculationClientMock;
//    @Mock
//    private ScoringDataDTO scoringDataDTO;
//    @Mock
//    private CreditRepository creditRepositoryMock;
//    @InjectMocks
//    private CalculateService calculateService;
//
//
//    @Test
//    public void testFinishRegistration() throws ResourceNotFoundException {
//        // Arrange
//        Long id = 1L;
//        application.setApplicationId(id);
//        client.setLast_name("Ivanov");
//        client.setFirst_name("Ivan");
//        client.setMiddle_name("Ivanovich");
//        client.setBirth_date(LocalDate.of(1990, 1, 1));
//        passport.setSeries("1234");
//        passport.setNumber("567890");
//        client.setPassport(passport);
//        application.setClient(client);
//
//    //    when(applicationRepositoryMock.findById(id)).thenReturn(Optional.of(application));
//
//      //  when(conveyorCalculationClientMock.calculateLoanOffers(scoringDataDTO)).thenReturn(new CreditDTO());
//
//
//       // calculateService.finishRegistration(id, request);
//
//        // Assert
//        verify(applicationRepositoryMock).findById(id);
//        verify(conveyorCalculationClientMock).calculateLoanOffers(scoringDataDTO);
//        verify(creditRepositoryMock).save(any(Credit.class));
//        verify(applicationRepositoryMock).save(any(Application.class));
//    }
//}

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    private ApplicationRepository applicationRepositoryMock;

    @Mock
    private ConveyorCalculationClient conveyorCalculationClientMock;

    @Mock
    private CreditRepository creditRepositoryMock;


    @InjectMocks
    private CalculateService calculateService;


    @Test
    public void testFinishRegistration() throws ResourceNotFoundException {
        // Arrange
        Long id = 1L;
        FinishRegistrationRequestDTO request = new FinishRegistrationRequestDTO();
        Application application = new Application();
        application.setApplicationId(id);
        Client client = new Client();
        client.setLast_name("Ivanov");
        client.setFirst_name("Ivan");
        client.setMiddle_name("Ivanovich");
        client.setBirth_date(LocalDate.of(1990, 1, 1));
        Passport passport = new Passport();
        passport.setSeries("1234");
        passport.setNumber("567890");
        client.setPassport(passport);
        application.setClient(client);
        when(applicationRepositoryMock.findById(id)).thenReturn(Optional.of(application));
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        when(conveyorCalculationClientMock.calculateLoanOffers(scoringDataDTO)).thenReturn(new CreditDTO());

        // Act
      //  calculateService.finishRegistration(id, request);

        // Assert
        verify(applicationRepositoryMock).findById(id);
        verify(conveyorCalculationClientMock).calculateLoanOffers(scoringDataDTO);
        verify(creditRepositoryMock).save(any(Credit.class));
        verify(applicationRepositoryMock).save(any(Application.class));


//        @Test
//        public void testSaveCredit () {
//            // Arrange
//            Application application = new Application();
//            application.setApplicationId(1L);
//            ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
//            CreditDTO creditDTO = new CreditDTO();
//            creditDTO.setAmount(new BigDecimal("1000"));
//            creditDTO.setTerm(12L);
//            creditDTO.setMonthlyPayment(new BigDecimal("100"));
//            creditDTO.setRate(new BigDecimal("0.1"));
//            creditDTO.setPsk(new BigDecimal("0.2"));
//            creditDTO.setPaymentSchedule("Monthly");
//            creditDTO.setIsInsuranceEnabled(true);
//            creditDTO.setIsSalaryClient(true);
//            when(conveyorCalculationClientMock.calculateLoanOffers(scoringDataDTO)).thenReturn(creditDTO);
//            Credit credit = new Credit();
//            credit.setApplication(application);
//            credit.setCredit_id(application.getApplicationId());
//            credit.setAmount(creditDTO.getAmount());
//            credit.setTerm(creditDTO.getTerm());
//            credit.setMonthly_payment(creditDTO.getMonthlyPayment());
//            credit.setRate(creditDTO.getRate());
//            credit.setPsk(creditDTO.getPsk());
//            credit.setPayment_schedule(creditDTO.getPaymentSchedule());
//            credit.setInsurance_enable(creditDTO.isIsInsuranceEnabled());
//            credit.setSalary_client(creditDTO.isIsSalaryClient());
//            credit.setCredit_status(CreditStatus.CALCULATED);
//
//            // Act
//            calculateService.saveCredit(application, scoringDataDTO);
//
//            // Assert
//            verify(conveyorCalculationClientMock).calculateLoanOffers(scoringDataDTO);
//            verify(creditRepositoryMock).save(credit);


        }
    }