package ru.nkashlev.loan_deal_app.deal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nkashlev.loan_deal_app.deal.entity.Application;

import java.util.Map;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

@Query(value = "SELECT a.applied_offer ->> 'term' AS term, a.applied_offer ->> 'isInsuranceEnabled' AS " +
        "isInsuranceEnabled, a.applied_offer ->> 'isSalaryClient' AS isSalaryClient FROM application" +
        " a WHERE a.application_id = :applicationId", nativeQuery = true)
Map<String, Object> getAppliedOfferFields(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT a.applied_offer ->> 'term' AS term FROM application  a WHERE a.application_id = :applicationId", nativeQuery = true)
    Map<String, Object> getAppliedOfferTerm(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT a.applied_offer ->> 'isInsuranceEnabled' AS isInsuranceEnabled FROM application a WHERE a.application_id = :applicationId", nativeQuery = true)
    Map<String, Object> getAppliedOfferIsInsuranceEnabled(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT a.applied_offer ->> 'isSalaryClient' AS isSalaryClient FROM application a WHERE a.application_id = :applicationId", nativeQuery = true)
    Map<String, Object> getAppliedOfferIsSalaryClient(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT a.applied_offer ->> 'requestedAmount' AS requestedAmount FROM application a WHERE a.application_id = :applicationId", nativeQuery = true)
    Map<String, Object> getAppliedOfferRequestedAmount(@Param("applicationId") Long applicationId);
}
