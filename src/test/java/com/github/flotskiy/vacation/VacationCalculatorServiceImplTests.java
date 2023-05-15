package com.github.flotskiy.vacation;

import com.github.flotskiy.vacation.dto.VacationPayDto;
import com.github.flotskiy.vacation.service.VacationCalculatorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationCalculatorServiceImplTests {

    private final VacationCalculatorServiceImpl service;

    @Autowired
    public VacationCalculatorServiceImplTests(VacationCalculatorServiceImpl service) {
        this.service = service;
    }

    private String avgSalary;
    private String numberOfDays;
    private String wrongNumberOfDays;
    private String successResult;
    private String wrongNumberOfDaysResult;

    @BeforeEach
    void setUp() {
        avgSalary = "50000";
        numberOfDays = "28";
        wrongNumberOfDays = "-1";
        successResult = "The amount of vacation pay is: 41569.97";
        wrongNumberOfDaysResult = "The entered number cannot be less than one";
    }

    @AfterEach
    void tearDown() {
        avgSalary = null;
        numberOfDays = null;
        wrongNumberOfDays = null;
        successResult = null;
        wrongNumberOfDaysResult = null;
    }

    @Test
    void getVacationPaySuccessTest() {
        VacationPayDto vacationPayDto = service.getVacationPay(avgSalary, numberOfDays, null);
        assertEquals(successResult, vacationPayDto.getResult());
    }

    @Test
    void getVacationPayFailTest() {
        VacationPayDto vacationPayDto = service.getVacationPay(avgSalary, wrongNumberOfDays, null);
        assertEquals(wrongNumberOfDaysResult, vacationPayDto.getResult());
    }
}
