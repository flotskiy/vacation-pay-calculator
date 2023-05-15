package com.github.flotskiy.vacation.service;

import com.github.flotskiy.vacation.dto.VacationPayDto;

public interface VacationCalculatorService {

    VacationPayDto getVacationPay(String averageAnnualSalary, String vacationDuration);
}
