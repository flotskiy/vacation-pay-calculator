package com.github.flotskiy.vacation.dto;

public class VacationPayDto {

    private boolean isCalculated;
    private String result;

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
