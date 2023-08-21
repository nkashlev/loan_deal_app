package ru.nkashlev.loan_deal_app.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkashlev.loan_deal_app.deal.entity.Application;
import ru.nkashlev.loan_deal_app.deal.entity.Client;
import ru.nkashlev.loan_deal_app.deal.entity.util.Passport;
import ru.nkashlev.loan_deal_app.deal.model.LoanApplicationRequestDTO;
import ru.nkashlev.loan_deal_app.deal.model.LoanOfferDTO;
import ru.nkashlev.loan_deal_app.deal.repositories.ApplicationRepository;
import ru.nkashlev.loan_deal_app.deal.repositories.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final ClientRepository clientRepository;
    private final ApplicationRepository applicationRepository;
    private final ConveyorOfferClient conveyorOfferClient;
    private final Client client = new Client();

    public List<LoanOfferDTO> applyLoan(LoanApplicationRequestDTO request) {
        List<LoanOfferDTO> offers = conveyorOfferClient.calculateLoanOffers(request);
        saveClient(request);
        saveApplication(offers);
        return offers;
    }

    private void saveClient(LoanApplicationRequestDTO request) {
        client.setFirst_name(request.getFirstName());
        client.setMiddle_name(request.getMiddleName());
        client.setLast_name(request.getLastName());
        client.setBirth_date(request.getBirthdate());
        client.setEmail(request.getEmail());
        Passport passport = new Passport();
        passport.setNumber(request.getPassportNumber());
        passport.setSeries(request.getPassportSeries());
        client.setPassport(passport);
        clientRepository.save(client);
    }

    private void saveApplication(List<LoanOfferDTO> offers) {
        for (LoanOfferDTO loanOffer : offers) {
            Application application = new Application();
            application.setClient(client);
            applicationRepository.save(application);
            loanOffer.setApplicationId(application.getApplication_id());
        }
    }
}
