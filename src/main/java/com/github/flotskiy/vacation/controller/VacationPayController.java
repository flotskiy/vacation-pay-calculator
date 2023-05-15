package com.github.flotskiy.vacation.controller;

import com.github.flotskiy.vacation.dto.VacationPayDto;
import com.github.flotskiy.vacation.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VacationPayController {

    private final VacationCalculatorService vacationCalculatorService;

    @Autowired
    public VacationPayController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @RequestMapping("/")
    public String openIndexPage() {
        return "index";
    }

    @GetMapping("/calculate")
    public ResponseEntity<VacationPayDto> getVacationPayValue(
            @RequestParam("salary") String salary, @RequestParam("duration") String duration) {
        return ResponseEntity.ok(vacationCalculatorService.getVacationPay(salary, duration));
    }
}
