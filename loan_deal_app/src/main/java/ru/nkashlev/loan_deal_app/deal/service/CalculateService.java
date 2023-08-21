package ru.nkashlev.loan_deal_app.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkashlev.loan_deal_app.deal.entity.Application;
import ru.nkashlev.loan_deal_app.deal.exceptions.ResourceNotFoundException;
import ru.nkashlev.loan_deal_app.deal.model.CreditDTO;
import ru.nkashlev.loan_deal_app.deal.model.FinishRegistrationRequestDTO;
import ru.nkashlev.loan_deal_app.deal.model.ScoringDataDTO;
import ru.nkashlev.loan_deal_app.deal.repositories.ApplicationRepository;


@Service
@RequiredArgsConstructor
public class CalculateService {


    private final ApplicationRepository applicationRepository;

    private final ScoringDataDTO scoringDataDTO;

    private final ConveyorCalculationClient conveyorCalculationClient;

    public void finishRegistration(FinishRegistrationRequestDTO request, Long id) throws ResourceNotFoundException {
        Application application = applicationRepository.findById(id).orElse(null);
        if (application == null) {
            throw new ResourceNotFoundException("Cannot find application with id: " + id);
        }




       // scoringDataDTO.setAmount(application.getClient().getDependent_amount());
        // term
        scoringDataDTO.setLastName(application.getClient().getLast_name());
        scoringDataDTO.setFirstName(application.getClient().getFirst_name());
        scoringDataDTO.setMiddleName(application.getClient().getMiddle_name());
        scoringDataDTO.setBirthdate(application.getClient().getBirth_date());
        // scoringDataDTO.setGender(request.getGender());
        // scoringDataDTO.setLastName(application.getClient().getBirth_date());
        scoringDataDTO.setPassportSeries(application.getClient().getPassport().getSeries());
        scoringDataDTO.setPassportNumber(application.getClient().getPassport().getNumber());
        scoringDataDTO.setPassportIssueDate(request.getPassportIssueDate());
        scoringDataDTO.setPassportIssueBranch(request.getPassportIssueBranch());
        //scoringDataDTO.setMaritalStatus(request.getMaritalStatus());
        scoringDataDTO.setDependentAmount(request.getDependentAmount());
        scoringDataDTO.setEmployment(request.getEmployment());
        scoringDataDTO.setAccount(request.getAccount());
        //todo


        CreditDTO creditDTO = conveyorCalculationClient.calculateLoanOffers(scoringDataDTO);








    }
}
