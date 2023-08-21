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

@Service
@RequiredArgsConstructor
public class OfferService {
    private final ApplicationRepository applicationRepository;

    public void updateApplication(LoanOfferDTO request) throws ResourceNotFoundException {
        Application application = applicationRepository.findById(request.getApplicationId()).orElse(null);
        if (application == null) {
            throw new ResourceNotFoundException("Cannot find application with id: " + request.getApplicationId());
        }
        // Обновление статуса заявки
        application.setStatus(ApplicationStatusHistoryDTO.StatusEnum.PREAPPROVAL);

        List<ApplicationStatusHistoryDTO> applicationStatusHistoryDTOList = new ArrayList<>();
        // Обновление истории статусов заявки
        ApplicationStatusHistoryDTO statusHistory = new ApplicationStatusHistoryDTO();
        statusHistory.setStatus(ApplicationStatusHistoryDTO.StatusEnum.PREAPPROVAL);
        statusHistory.setTime(LocalDate.now());
        statusHistory.setChangeType(ApplicationStatusHistoryDTO.ChangeTypeEnum.AUTOMATIC);
        applicationStatusHistoryDTOList.add(statusHistory);
        application.setStatus_history(applicationStatusHistoryDTOList);
        application.setApplied_offer(request);
        applicationRepository.save(application);
    }
}

