package ru.nkashlev.loan_deal_app.deal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nkashlev.loan_deal_app.deal.api.DefaultApi;
import ru.nkashlev.loan_deal_app.deal.exceptions.ResourceNotFoundException;
import ru.nkashlev.loan_deal_app.deal.model.FinishRegistrationRequestDTO;
import ru.nkashlev.loan_deal_app.deal.service.CalculateService;

@RestController
@RequiredArgsConstructor
public class LoanCalculateController implements DefaultApi {

    private final CalculateService calculateService;


    @Override
    public ResponseEntity<Void> dealCalculateApplicationIdPut(@RequestBody FinishRegistrationRequestDTO request, @PathVariable Long id) {
        try {
            calculateService.finishRegistration(request, id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }

    }
}
