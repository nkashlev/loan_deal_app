package ru.nkashlev.loan_deal_app.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nkashlev.loan_deal_app.deal.entity.Application;
import ru.nkashlev.loan_deal_app.deal.exceptions.ResourceNotFoundException;
import ru.nkashlev.loan_deal_app.deal.model.ApplicationStatusHistoryDTO;
import ru.nkashlev.loan_deal_app.deal.model.LoanOfferDTO;
import ru.nkashlev.loan_deal_app.deal.repositories.ApplicationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final ApplicationRepository applicationRepository;

    public void updateApplication(LoanOfferDTO request) throws ResourceNotFoundException {

        if (request.getApplicationId() == null) {
            throw new IllegalArgumentException("Application id cannot be null");
        }

        Optional<Application> optionalApplication = applicationRepository.findById(request.getApplicationId());
        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            application.setStatus(ApplicationStatusHistoryDTO.StatusEnum.PREAPPROVAL);

            // Обновление истории статусов заявки
            List<ApplicationStatusHistoryDTO> applicationStatusHistoryDTOList = new ArrayList<>();
            ApplicationStatusHistoryDTO statusHistory = new ApplicationStatusHistoryDTO();
            statusHistory.setStatus(ApplicationStatusHistoryDTO.StatusEnum.PREAPPROVAL);
            statusHistory.setTime(LocalDate.now());
            statusHistory.setChangeType(ApplicationStatusHistoryDTO.ChangeTypeEnum.AUTOMATIC);
            applicationStatusHistoryDTOList.add(statusHistory);
            application.setStatusHistory(applicationStatusHistoryDTOList);
            application.setAppliedOffer(request);
            applicationRepository.save(application);
        } else {
            throw new ResourceNotFoundException("Cannot find application with id: " + request.getApplicationId());
        }


//        Application application = applicationRepository.findById(request.getApplicationId()).orElse(null);
//        if (application == null) {
//            throw new ResourceNotFoundException("Cannot find application with id: " + request.getApplicationId());
//        }
        // Обновление статуса заявки

    }
}

