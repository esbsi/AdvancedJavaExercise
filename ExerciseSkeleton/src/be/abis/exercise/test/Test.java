package be.abis.exercise.test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {

        // 1.a.
        LocalDate now = LocalDate.now();
        System.out.println(now.plusYears(3).plusMonths(2).plusDays(15));

        //1.b.
        LocalDate bd = LocalDate.of(1987, 3, 19);
        System.out.println(bd.getDayOfWeek());

        //1.c
        Period untilBd = Period.between(now, bd.plusYears(36));
        System.out.println(untilBd.getMonths() + " Months " + untilBd.getDays() + " days.");

        //1.d
//        System.out.println(Period.between(bd, now).getDays());
        System.out.println(ChronoUnit.DAYS.between(bd, now) + " Days.");


        //1.e
//        TreeSet<String> zones = new TreeSet<>(ZoneId.getAvailableZoneIds());
//        System.out.println(zones);
        ZonedDateTime timeInKolkata = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        Duration timeDifference = Duration.between(LocalDateTime.now(), timeInKolkata.toLocalDateTime());
        System.out.println("It is " + timeDifference.toHours() + " hours and " + timeDifference.toMinutes()%60 + " minutes later in Kolkata.");






    }
}
