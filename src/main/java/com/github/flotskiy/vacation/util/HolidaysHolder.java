package com.github.flotskiy.vacation.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class HolidaysHolder {

    private static final List<LocalDate> UNPAID_HOLIDAYS = initUnpaidHolidays();

    private HolidaysHolder() {}

    public static List<LocalDate> getUnpaidHolidays() {
        return UNPAID_HOLIDAYS;
    }

    private static List<LocalDate> initUnpaidHolidays() {
        List<LocalDate> result = new ArrayList<>();
        for (int year = 2010; year <= 2025; year++) {
            result.add(LocalDate.of(year, Month.JANUARY, 1));
            result.add(LocalDate.of(year, Month.JANUARY, 2));
            result.add(LocalDate.of(year, Month.JANUARY, 3));
            result.add(LocalDate.of(year, Month.JANUARY, 4));
            result.add(LocalDate.of(year, Month.JANUARY, 5));
            result.add(LocalDate.of(year, Month.JANUARY, 6));
            result.add(LocalDate.of(year, Month.JANUARY, 7));
            result.add(LocalDate.of(year, Month.JANUARY, 8));
            result.add(LocalDate.of(year, Month.FEBRUARY, 23));
            result.add(LocalDate.of(year, Month.MARCH, 8));
            result.add(LocalDate.of(year, Month.MAY, 1));
            result.add(LocalDate.of(year, Month.MAY, 9));
            result.add(LocalDate.of(year, Month.JUNE, 12));
            result.add(LocalDate.of(year, Month.NOVEMBER, 4));
        }
        return result;
    }
}
