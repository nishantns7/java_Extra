package com.zemoso.assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.WEEKS;
import static java.time.temporal.TemporalAdjusters.*;

public class VacationWeeks {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = 0, startMonth = 0, endMonth = 0;
        try {
            System.out.println("Enter the year");
            year = Integer.parseInt(br.readLine());
            System.out.println("Enter the name of the starting month");
            startMonth = Month.valueOf(br.readLine().toUpperCase()).getValue();
            System.out.println("Enter the name of the ending month");
            endMonth = Month.valueOf(br.readLine().toUpperCase()).getValue();
        } catch (Exception e) {
            System.out.println("Invalid year or month.");
        }

        //1. Find first Monday

        LocalDate firstMonday = LocalDate.of(year, startMonth, 1);
        int dayOfWeek = firstMonday.getDayOfWeek().getValue();
        if(dayOfWeek != DayOfWeek.MONDAY.getValue())
            if (dayOfWeek == DayOfWeek.SUNDAY.getValue())
                firstMonday = firstMonday.plusDays(1);
            else
                firstMonday = firstMonday.plusDays(8 - dayOfWeek);

        //2. Find last Sunday

        LocalDate lastSunday = LocalDate.of(year, endMonth, 1).with(lastDayOfMonth());
        if(lastSunday.getDayOfWeek() != DayOfWeek.SUNDAY)
            lastSunday = lastSunday.minusDays(lastSunday.getDayOfWeek().getValue());

        //3. Calculate duration in weeks

//        long duration = WEEKS.between(firstMonday,lastSunday);
        long duration = firstMonday.until(lastSunday, WEEKS);
        System.out.println("Number of weeks available for vacation: " + (duration + 1));        //Adding one because the end date is excluded

    }

}
