package com.github.flotskiy.vacation.service;

import com.github.flotskiy.vacation.dto.VacationPayDto;
import com.github.flotskiy.vacation.exceptions.WrongValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    @Value("${average-month-duration}")
    private String averageMonthDuration;

    @Value("${tax-rate}")
    private String inputTaxRate;

    @Value("${success.message}")
    private String successMessage;

    @Value("${success.message}")
    private String successLogMessage;

    @Value("${fail.general}")
    private String generalFailMessage;

    @Value("${fail.parse}")
    private String parseFailMessage;

    @Value("${fail.number.exception}")
    private String numberExceptionMessage;

    @Value("${fail.number.message}")
    private String wrongNumberMessage;

    @Value("${fail.log}")
    private String failLogMessage;

    private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    @Override
    public VacationPayDto getVacationPay(String averageAnnualSalary, String vacationDuration) {
        VacationPayDto vacationPayDto = new VacationPayDto();
        try {
            String vacationPayStr = calculateVacationPay(averageAnnualSalary, vacationDuration);
            logger.info(successLogMessage, averageAnnualSalary, vacationDuration, vacationPayStr);
            vacationPayDto.setCalculated(true);
            vacationPayDto.setResult(successMessage + vacationPayStr);
        } catch (WrongValueException wve) {
            logger.info(failLogMessage, wve);
            vacationPayDto.setResult(wrongNumberMessage);
        } catch (NumberFormatException nfe) {
            logger.info(failLogMessage, nfe);
            vacationPayDto.setResult(parseFailMessage);
        } catch (Exception e) {
            logger.info(failLogMessage, e);
            vacationPayDto.setResult(generalFailMessage);
        }
        return vacationPayDto;
    }

    private String calculateVacationPay(String inputAverageSalary, String inputDuration) {
        if (Integer.parseInt(inputAverageSalary) < 1 || Integer.parseInt(inputDuration) < 1) {
            throw new WrongValueException(numberExceptionMessage);
        }
        BigDecimal averageSalary = new BigDecimal(inputAverageSalary);
        BigDecimal monthDuration = new BigDecimal(averageMonthDuration);
        BigDecimal duration = new BigDecimal(inputDuration);
        BigDecimal taxRate = new BigDecimal(inputTaxRate);

        BigDecimal vacationPayBeforeTax =
                averageSalary.divide(monthDuration, 10, RoundingMode.HALF_DOWN).multiply(duration);
        BigDecimal taxValue = vacationPayBeforeTax.multiply(taxRate);
        BigDecimal vacationPay = vacationPayBeforeTax.subtract(taxValue).setScale(2, RoundingMode.HALF_DOWN);
        return vacationPay.toString();
    }
}
