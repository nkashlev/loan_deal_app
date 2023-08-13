package ru.nkashlev.loan_deal_app.deal;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@RequiredArgsConstructor
public class Passport {

    private String series;
    private String number;
    private String issue_branch;
    private LocalDate issue_date;
}
