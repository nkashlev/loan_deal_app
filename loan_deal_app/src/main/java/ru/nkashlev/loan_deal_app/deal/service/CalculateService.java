package ru.nkashlev.loan_deal_app.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkashlev.loan_deal_app.deal.entity.Application;
import ru.nkashlev.loan_deal_app.deal.entity.Credit;
import ru.nkashlev.loan_deal_app.deal.entity.util.CreditStatus;
import ru.nkashlev.loan_deal_app.deal.entity.util.StatusHistory;
import ru.nkashlev.loan_deal_app.deal.exceptions.ResourceNotFoundException;
import ru.nkashlev.loan_deal_app.deal.model.ApplicationStatusHistoryDTO;
import ru.nkashlev.loan_deal_app.deal.model.CreditDTO;
import ru.nkashlev.loan_deal_app.deal.model.FinishRegistrationRequestDTO;
import ru.nkashlev.loan_deal_app.deal.model.ScoringDataDTO;
import ru.nkashlev.loan_deal_app.deal.repositories.ApplicationRepository;
import ru.nkashlev.loan_deal_app.deal.repositories.CreditRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CalculateService {


    private final ApplicationRepository applicationRepository;

    private final ScoringDataDTO scoringDataDTO;

    private final ConveyorCalculationClient conveyorCalculationClient;

    private final CreditRepository creditRepository;

    private boolean existById(Long id) {
        return applicationRepository.existsById(id);
    }
    public void finishRegistration(Long id, FinishRegistrationRequestDTO request) throws ResourceNotFoundException {
        System.out.println(existById(id) + " !!!!!!!!!!!!!!!!!!!!!!!!!");
//        List<Application> applications = applicationRepository.findAll();
        //System.out.println(applications);
        Application application = findByIdApplication(id);

        setScoringDTO(application, request);
        saveCredit(application);
        updateApplicationStatusHistory(application);
    }

    private Application findByIdApplication(Long id) throws ResourceNotFoundException {
        Application application = applicationRepository.findById(id).orElse(null);
        if (application == null) {
            throw new ResourceNotFoundException("Cannot find application with id: " + id);
        }
        return application;
    }

    private void updateApplicationStatusHistory(Application application) {

        List<ApplicationStatusHistoryDTO> applicationStatusHistoryDTOList =  new ArrayList<>();
        ApplicationStatusHistoryDTO statusHistory = new ApplicationStatusHistoryDTO();
        statusHistory.setStatus(ApplicationStatusHistoryDTO.StatusEnum.PREAPPROVAL);
        statusHistory.setTime(LocalDate.now());
        statusHistory.setChangeType(ApplicationStatusHistoryDTO.ChangeTypeEnum.AUTOMATIC);
        applicationStatusHistoryDTOList.add(statusHistory);
        application.setStatusHistory(applicationStatusHistoryDTOList);
        applicationRepository.save(application);
    }


    private void saveCredit(Application application) {
        CreditDTO creditDTO = conveyorCalculationClient.calculateLoanOffers(scoringDataDTO);
        Credit credit = new Credit();
        credit.setApplication(application);
        credit.setCredit_id(credit.getCredit_id());
        credit.setAmount(creditDTO.getAmount());
        credit.setTerm(creditDTO.getTerm());
        credit.setMonthly_payment(creditDTO.getMonthlyPayment());
        credit.setRate(creditDTO.getRate());
        credit.setPsk(creditDTO.getPsk());
        credit.setPayment_schedule(credit.getPayment_schedule());
        credit.setInsurance_enable(creditDTO.isIsInsuranceEnabled());
        credit.setSalary_client(creditDTO.isIsSalaryClient());
        credit.setCredit_status(CreditStatus.CALCULATED);
        creditRepository.save(credit);
    }


    private void setScoringDTO(Application application, FinishRegistrationRequestDTO request) {
        scoringDataDTO.setLastName(application.getClient().getLast_name());
        scoringDataDTO.setFirstName(application.getClient().getFirst_name());
        scoringDataDTO.setMiddleName(application.getClient().getMiddle_name());
        scoringDataDTO.setBirthdate(application.getClient().getBirth_date());
        scoringDataDTO.setGender(application.getClient().getGender()); // поле gender пусто в таблице клиент
        scoringDataDTO.setPassportSeries(application.getClient().getPassport().getSeries());
        scoringDataDTO.setPassportNumber(application.getClient().getPassport().getNumber());
        scoringDataDTO.setPassportIssueDate(request.getPassportIssueDate());
        scoringDataDTO.setPassportIssueBranch(request.getPassportIssueBranch());
        scoringDataDTO.setMaritalStatus(application.getClient().getMarital_status());// поле MaritalStatus пусто в таблице клиент
        scoringDataDTO.setDependentAmount(request.getDependentAmount());
        scoringDataDTO.setEmployment(request.getEmployment());
        scoringDataDTO.setAccount(request.getAccount());
    }
}
